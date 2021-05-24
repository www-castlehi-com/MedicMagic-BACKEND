package MedicMagic.userReminder.userWaterIntake.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userReminder.userWaterIntake.domain.UserWaterIntake;
import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserWaterIntakeJDBC implements UserWaterIntakeDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserWaterIntakeDto> userWaterIntakeDtoRowMapper = new RowMapper<UserWaterIntakeDto>() {
        @Override
        public UserWaterIntakeDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserWaterIntake userWaterIntake = new UserWaterIntake();
            userWaterIntake.setId(resultSet.getString("id"));
            userWaterIntake.setCups(resultSet.getDouble("cups"));
            userWaterIntake.setWaterTime(resultSet.getString("waterTime"));
            UserWaterIntakeDto userWaterIntakeDto = new UserWaterIntakeDto(userWaterIntake);
            return userWaterIntakeDto;
        }
    };

    @Override
    public void add(UserWaterIntakeDto userWaterIntakeDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userWaterIntakeAdd"), userWaterIntakeDto.id, userWaterIntakeDto.cups, userWaterIntakeDto.waterTime);
    }

    @Override
    public UserWaterIntakeDto get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userWaterIntakeGet"), new Object[]{id}, this.userWaterIntakeDtoRowMapper);
    }

    @Override
    public List<UserWaterIntakeDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userWaterIntakeGetAll"), this.userWaterIntakeDtoRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userWaterIntakeDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userWaterIntakeDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userWaterIntakeGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userWaterIntakeGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserWaterIntakeDto userWaterIntakeDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userWaterIntakeUpdate"), userWaterIntakeDto.cups, userWaterIntakeDto.waterTime, userWaterIntakeDto.id);
    }
}
