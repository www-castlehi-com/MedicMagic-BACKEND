package MedicMagic.userReminder.userSleep.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userReminder.userSleep.domain.UserSleep;
import MedicMagic.userReminder.userSleep.dto.UserSleepDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserSleepJDBC implements UserSleepDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserSleepDto> userSleepDtoRowMapper = new RowMapper<UserSleepDto>() {
        @Override
        public UserSleepDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserSleep userSleep = new UserSleep();
            userSleep.setId(resultSet.getString("id"));
            userSleep.setSleepGoal(resultSet.getDouble("sleepGoal"));
            UserSleepDto userSleepDto = new UserSleepDto(userSleep);
            return userSleepDto;
        }
    };

    @Override
    public void add(UserSleepDto userSleepDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userSleepAdd"), userSleepDto.id, userSleepDto.sleepGoal);
    }

    @Override
    public UserSleepDto get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userSleepGet"), new Object[]{id}, this.userSleepDtoRowMapper);
    }

    @Override
    public List<UserSleepDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userSleepGetAll"), this.userSleepDtoRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userSleepDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userSleepDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userSleepGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userSleepGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserSleepDto userSleepDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userSleepUpdate"), userSleepDto.sleepGoal, userSleepDto.id);
    }
}
