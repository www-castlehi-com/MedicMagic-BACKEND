package MedicMagic.userReminder.userHospital.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userReminder.userHospital.dao.UserHospitalDao;
import MedicMagic.userReminder.userHospital.domain.UserHospital;
import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserHospitalJDBC implements UserHospitalDao {
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserHospitalDto> userHospitalDtoRowMapper = new RowMapper<UserHospitalDto>() {
        @Override
        public UserHospitalDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserHospital userHospital = new UserHospital();
            userHospital.setId(resultSet.getString("id"));
            userHospital.setHospitalDate(resultSet.getString("hospitalDate"));
            userHospital.setHospitalTime(resultSet.getString("hospitalTime"));
            UserHospitalDto userHospitalDto = new UserHospitalDto(userHospital);
            return userHospitalDto;
        }
    };

    @Override
    public void add(UserHospitalDto userHospitalDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userHospitalAdd"), userHospitalDto.id, userHospitalDto.hospitalDate, userHospitalDto.hospitalTime);
    }

    @Override
    public UserHospitalDto get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userHospitalGet"), new Object[]{id}, userHospitalDtoRowMapper);
    }

    @Override
    public List<UserHospitalDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userHospitalGetAll"), this.userHospitalDtoRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userHospitalDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userHospitalDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userHospitalGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userHospitalGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserHospitalDto userHospitalDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userHospitalUpdate"), userHospitalDto.hospitalDate, userHospitalDto.hospitalTime, userHospitalDto.id);
    }
}
