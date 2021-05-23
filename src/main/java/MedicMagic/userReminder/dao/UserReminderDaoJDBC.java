package MedicMagic.userReminder.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.user.DuplicateUserIdException;
import MedicMagic.userReminder.domain.UserReminder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserReminderDaoJDBC implements UserReminderDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserReminder> userReminderRowMapper = new RowMapper<UserReminder>() {
        @Override
        public UserReminder mapRow(ResultSet resultSet, int i) throws SQLException {
            UserReminder userReminder = new UserReminder();
            userReminder.setId(resultSet.getString("id"));
            userReminder.setBirthControlPills(resultSet.getBoolean("birthControlPills"));
            userReminder.setBeforeBirthControlPills(resultSet.getInt("beforeBirthControlPills"));
            userReminder.setBirthControlPillsTime(resultSet.getString("birthControlPillsTime"));
            userReminder.setPhysiology(resultSet.getBoolean("physiology"));
            userReminder.setBeforePhysiology(resultSet.getInt("beforePhysiology"));
            userReminder.setSleepTimeGoal(resultSet.getBoolean("sleepTimeGoal"));
            userReminder.setExerciseTimeGoal(resultSet.getBoolean("exerciseTimeGoal"));
            userReminder.setHospital(resultSet.getBoolean("hospital"));
            userReminder.setHospitalDate(resultSet.getInt("hospitalDate"));

            return userReminder;
        }
    };

    @Override
    public void add(String id) throws DuplicateUserIdException {
        try {
            this.jdbcTemplate.update(this.sqlService.getSql("userReminderAdd"), id);
        } catch(DuplicateKeyException e) {
            throw new DuplicateUserIdException(e);
        }
    }

    @Override
    public UserReminder get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderGet"), new Object[]{id}, this.userReminderRowMapper);
    }

    @Override
    public List<UserReminder> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userReminderGetAll"), this.userReminderRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderGetCount"), Integer.class);
    }

    @Override
    public void update(UserReminder userReminder) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderUpdate"),
                userReminder.isBirthControlPills(),
                nullCheck("beforeBirthControlPills", userReminder.getBeforeBirthControlPills()),
                nullCheck("birthControlPillsTime", userReminder.getBirthControlPillsTime()),
                userReminder.isPhysiology(),
                nullCheck("beforePhysiology", userReminder.getBeforePhysiology()),
                userReminder.isSleepTimeGoal(),
                userReminder.isExerciseTimeGoal(),
                userReminder.isHospital(),
                nullCheck("hospitalDate", userReminder.getHospitalDate()),
                userReminder.getId()
        );
    }

    private Object nullCheck(String column, Object object) {
        if(column == "beforeBirthControlPills" && Integer.parseInt(object.toString()) == 0) { object = 21; }
        else if(column == "birthControlPillsTime") {
            if(object == "08:00:00") { object = java.sql.Time.valueOf("08:00:00"); }
            else { object = java.sql.Time.valueOf(object.toString()); }
        }
        else if(column == "beforePhysiology" && Integer.parseInt(object.toString()) == 0) { object = 3; }
        else if(column == "hospitalDate" && Integer.parseInt(object.toString()) == 0) { object = 1; }

        return object;
    }
}
