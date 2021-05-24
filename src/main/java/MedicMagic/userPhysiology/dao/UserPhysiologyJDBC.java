package MedicMagic.userPhysiology.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.exception.LastValueNullException;
import MedicMagic.userPhysiology.domain.UserPhysiology;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserPhysiologyJDBC implements UserPhysiologyDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserPhysiology> userPhysiologyRowMapper = new RowMapper<UserPhysiology>() {
        @Override
        public UserPhysiology mapRow(ResultSet resultSet, int i) throws SQLException {
            UserPhysiology userPhysiology = new UserPhysiology();
            userPhysiology.setId(resultSet.getString("id"));
            userPhysiology.setStartPhysiology(resultSet.getString("startPhysiology"));
            userPhysiology.setEndPhysiology(resultSet.getString("endPhysiology"));
            userPhysiology.setExpectedOvulationDate(resultSet.getString("expectedOvulationDate"));
            userPhysiology.setExpectedPhysiologyDate(resultSet.getString("expectedPhysiologyDate"));
            return userPhysiology;
        }
    };

    @Override
    public void add(UserPhysiology userPhysiology){
        if(this.getCountEachId(userPhysiology.getId()) != 0 && this.getLastEachId(userPhysiology.getId()).getEndPhysiology() == null) {
            throw new LastValueNullException("이전 주기 입력을 완료해주세요");
        }
        this.jdbcTemplate.update(this.sqlService.getSql("userPhysiologyAdd"), userPhysiology.getId(), java.sql.Date.valueOf(userPhysiology.getStartPhysiology()).toString());
    }

    @Override
    public List<UserPhysiology> get(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userPhysiologyGet"), new Object[]{id}, this.userPhysiologyRowMapper);
    }

    @Override
    public UserPhysiology getEachIdAndStartPhysiology(String id, String startPhysiology) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetEachIdAndDate"), new Object[]{id, startPhysiology}, this.userPhysiologyRowMapper);
    }

    @Override
    public UserPhysiology getLastEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetLastEachId"), new Object[]{id}, this.userPhysiologyRowMapper);
    }

    @Override
    public List<UserPhysiology> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userPhysiologyGetAll"), this.userPhysiologyRowMapper);
    }

    @Override
    public List<UserPhysiology> getEachIdLimit3(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userPhysiologyGetEachIdLimit3"), new Object[]{id}, this.userPhysiologyRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userPhysiologyDeleteAll"));
    }

    @Override
    public void deleteEachIdAndStartPhysiology(String id, String startPhysiology) {
        this.jdbcTemplate.update(this.sqlService.getSql("userPhysiologyDeleteEachIdAndStartPhysiology"), id, startPhysiology);
    }

    @Override
    public void deleteEachIdAndEndPhysiology(String id, String endPhysiology) {
        this.jdbcTemplate.update(this.sqlService.getSql("userPhysiologyDeleteEachIdAndEndPhysiology"), id, endPhysiology);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserPhysiology userPhysiology) {
        this.jdbcTemplate.update(this.sqlService.getSql("userPhysiologyUpdate"),
                nullCheck("endPhysiology", userPhysiology.getEndPhysiology()),
                nullCheck("expectedOvulationDate", userPhysiology.getExpectedOvulationDate()),
                nullCheck("expectedPhysiologyDate", userPhysiology.getExpectedPhysiologyDate()),
                userPhysiology.getId(),
                java.sql.Date.valueOf(userPhysiology.getStartPhysiology().toString())
                );
    }

    private Object nullCheck(String column, Object object) {
        if(column == "endPhysiology" && object != "null") {
            return java.sql.Date.valueOf(object.toString());
        }
        else if(column == "expectedOvulationDate" && object != "null") {
            return java.sql.Date.valueOf(object.toString());
        }
        else if(column == "expectedPhysiologyDate" && object != "null") {
            return java.sql.Date.valueOf(object.toString());
        }
        else {
            return null;
        }
    }
}
