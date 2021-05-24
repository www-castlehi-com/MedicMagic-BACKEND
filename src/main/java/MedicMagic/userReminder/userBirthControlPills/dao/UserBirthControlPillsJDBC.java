package MedicMagic.userReminder.userBirthControlPills.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userReminder.userBirthControlPills.domain.UserBirthControlPills;
import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserBirthControlPillsJDBC implements UserBirthControlPillsDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserBirthControlPillsDto> userBirthControlPillsDtoRowMapper = new RowMapper<UserBirthControlPillsDto>() {
        @Override
        public UserBirthControlPillsDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserBirthControlPills userBirthControlPills = new UserBirthControlPills();
            userBirthControlPills.setId(resultSet.getString("id"));
            userBirthControlPills.setPillsTime(resultSet.getString("pillsTime"));
            userBirthControlPills.setPillsDate(resultSet.getString("pillsDate"));
            userBirthControlPills.setDays(resultSet.getInt("days"));
            UserBirthControlPillsDto userBirthControlPillsDto = new UserBirthControlPillsDto(userBirthControlPills);
            return userBirthControlPillsDto;
        }
    };

    @Override
    public void add(UserBirthControlPillsDto userBirthControlPillsDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userBirthControlPillsAdd"), userBirthControlPillsDto.id, userBirthControlPillsDto.pillsTime, userBirthControlPillsDto.pillsDate, userBirthControlPillsDto.days);
    }

    @Override
    public UserBirthControlPillsDto get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userBirthControlPillsGet"), new Object[]{id}, this.userBirthControlPillsDtoRowMapper);
    }

    @Override
    public List<UserBirthControlPillsDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userBirthControlPillsGetAll"), this.userBirthControlPillsDtoRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userBirthControlPillsDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userBirthControlPillsDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userBirthControlPillsGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userBirthControlPillsGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserBirthControlPillsDto userBirthControlPillsDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userBirthControlPillsUpdate"), userBirthControlPillsDto.pillsTime, userBirthControlPillsDto.pillsDate, userBirthControlPillsDto.days, userBirthControlPillsDto.id);
    }
}
