package MedicMagic.userReminder.userReminderList.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userReminder.userReminderList.domain.UserReminderList;
import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserReminderListJDBC implements UserReminderListDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserReminderListDto> userReminderListRowMapper = new RowMapper<UserReminderListDto>() {
        @Override
        public UserReminderListDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserReminderList userReminderList = new UserReminderList();
            userReminderList.setId(resultSet.getString("id"));
            userReminderList.setBirthControlPills(resultSet.getBoolean("birthControlPills"));
            userReminderList.setPhysiology(resultSet.getBoolean("physiology"));
            userReminderList.setHospital(resultSet.getBoolean("hospital"));
            userReminderList.setWaterIntake(resultSet.getBoolean("waterIntake"));
            userReminderList.setExerciseTimeGoal(resultSet.getBoolean("exerciseTimeGoal"));
            userReminderList.setSleepTimeGoal(resultSet.getBoolean("sleepTimeGoal"));
            UserReminderListDto userReminderListDto = new UserReminderListDto(userReminderList);
            return userReminderListDto;
        }
    };

    @Override
    public void add(UserReminderListDto userReminderListDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderListAdd"), userReminderListDto.id, userReminderListDto.birthControlPills, userReminderListDto.physiology, userReminderListDto.hospital, userReminderListDto.waterIntake, userReminderListDto.exerciseTimeGoal, userReminderListDto.sleepTimeGoal);
    }

    @Override
    public UserReminderListDto get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderListGet"), new Object[]{id}, this.userReminderListRowMapper);
    }

    @Override
    public List<UserReminderListDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userReminderListGetAll"), this.userReminderListRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderListDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderListDeleteEachId"), new Object[]{id});
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderListGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderListGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserReminderListDto userReminderListDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderListUpdate"), userReminderListDto.birthControlPills, userReminderListDto.physiology, userReminderListDto.hospital, userReminderListDto.waterIntake, userReminderListDto.exerciseTimeGoal, userReminderListDto.sleepTimeGoal, userReminderListDto.id);
    }
}
