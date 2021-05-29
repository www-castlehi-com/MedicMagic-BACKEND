package MedicMagic.userPhysiology.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.exception.LastValueNullException;
import MedicMagic.userPhysiology.domain.UserPhysiology;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;
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

    private RowMapper<UserPhysiologyDto> userPhysiologyRowMapper = new RowMapper<UserPhysiologyDto>() {
        @Override
        public UserPhysiologyDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserPhysiology userPhysiology = new UserPhysiology();
            userPhysiology.setId(resultSet.getString("id"));
            userPhysiology.setStartPhysiology(resultSet.getString("startPhysiology"));
            userPhysiology.setEndPhysiology(resultSet.getString("endPhysiology"));
            userPhysiology.setExpectedOvulationDate(resultSet.getString("expectedOvulationDate"));
            userPhysiology.setExpectedPhysiologyDate(resultSet.getString("expectedPhysiologyDate"));
            UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userPhysiology);
            return userPhysiologyDto;
        }
    };

    @Override
    public void add(UserPhysiologyDto userPhysiologyDto){
        if(this.getCountEachId(userPhysiologyDto.id) != 0 && this.getLastEachId(userPhysiologyDto.id).endPhysiology == null) {
            throw new LastValueNullException("이전 주기 입력을 완료해주세요");
        }
        this.jdbcTemplate.update(this.sqlService.getSql("userPhysiologyAdd"),
                userPhysiologyDto.id,
                java.sql.Date.valueOf(userPhysiologyDto.startPhysiology).toString(),
                nullCheck("endPhysiology", userPhysiologyDto.endPhysiology),
                nullCheck("expectedOvulationDate", userPhysiologyDto.expectedOvulationDate),
                nullCheck("expectedPhysiologyDate", userPhysiologyDto.expectedPhysiologyDate)
        );
    }

    @Override
    public List<UserPhysiologyDto> get(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userPhysiologyGet"), this.userPhysiologyRowMapper);
    }

    @Override
    public UserPhysiologyDto getEachIdAndStartPhysiology(String id, String startPhysiology) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetEachIdAndDate"), this.userPhysiologyRowMapper, new Object[]{id, startPhysiology});
    }

    @Override
    public UserPhysiologyDto getEachIdAndEndPhysiology(String id, String endPhysiology) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetEachIdAndEndPhysiology"), this.userPhysiologyRowMapper, new Object[]{id, endPhysiology});
    }

    @Override
    public UserPhysiologyDto getLastEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetLastEachId"), new Object[]{id}, this.userPhysiologyRowMapper);
    }

    @Override
    public UserPhysiologyDto getNull(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetNull"), new Object[]{id}, this.userPhysiologyRowMapper);
    }

    @Override
    public List<UserPhysiologyDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userPhysiologyGetAll"), this.userPhysiologyRowMapper);
    }

    @Override
    public List<UserPhysiologyDto> getEachIdLimit6(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userPhysiologyGetEachIdLimit6"), new Object[]{id}, this.userPhysiologyRowMapper);
    }

    @Override
    public List<UserPhysiologyDto> getEachIdAndMonth(String id, String month) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userPhysiologyGetEachIdAndMonth"), new Object[]{id, month, month}, this.userPhysiologyRowMapper);
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
    public int getCountEachIdAndStartPhysiology(String id, String startPhysiology) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetCountEachIdAndStartPhysiology"), new Object[]{id, startPhysiology}, Integer.class);
    }

    @Override
    public int getCountEachIdAndEndPhysiology(String id, String endPhysiology) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userPhysiologyGetCountEachIdAndEndPhysiology"), new Object[]{id, endPhysiology}, Integer.class);
    }

    @Override
    public void update(UserPhysiologyDto userPhysiologyDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userPhysiologyUpdate"),
                nullCheck("endPhysiology", userPhysiologyDto.endPhysiology),
                nullCheck("expectedOvulationDate", userPhysiologyDto.expectedOvulationDate),
                nullCheck("expectedPhysiologyDate", userPhysiologyDto.expectedPhysiologyDate),
                userPhysiologyDto.id,
                java.sql.Date.valueOf(userPhysiologyDto.startPhysiology)
                );
    }

    private Object nullCheck(String column, Object object) {
        if(column == "endPhysiology" && object != null) {
            return java.sql.Date.valueOf(object.toString());
        }
        else if(column == "expectedOvulationDate" && object != null) {
            return java.sql.Date.valueOf(object.toString());
        }
        else if(column == "expectedPhysiologyDate" && object != null) {
            return java.sql.Date.valueOf(object.toString());
        }
        else {
            return null;
        }
    }
}
