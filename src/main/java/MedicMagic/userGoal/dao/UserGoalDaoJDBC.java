package MedicMagic.userGoal.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.user.dao.DuplicateUserIdException;
import MedicMagic.userCalender.dao.NegativeException;
import MedicMagic.userGoal.domain.UserGoal;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserGoalDaoJDBC implements UserGoalDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserGoal> userGoalRowMapper =
            new RowMapper<UserGoal>() {
                @Override
                public UserGoal mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserGoal userGoal = new UserGoal();
                    userGoal.setId(resultSet.getString("id"));
                    userGoal.setWeigh(resultSet.getDouble("weigh"));
                    userGoal.setSleepTime(resultSet.getString("sleepTime"));
                    userGoal.setExerciseTime(resultSet.getString("exerciseTime"));
                    userGoal.setWaterIntake(resultSet.getDouble("waterIntake"));

                    return userGoal;
                }
            };


    @Override
    public void add(String id) throws DuplicateUserIdException {
        try {
            this.jdbcTemplate.update(this.sqlService.getSql("userGoalAdd"), id);
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserIdException(e);
        }
    }

    @Override
    public UserGoal get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGoalGet"), new Object[]{id}, this.userGoalRowMapper);
    }

    @Override
    public List<UserGoal> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userGoalGetAll"), this.userGoalRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userGoalDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userGoalDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForInt(this.sqlService.getSql("userGoalGetCount"));
    }

    @Override
    public void update(UserGoal userGoal) {
        this.jdbcTemplate.update(this.sqlService.getSql("userGoalUpdate"),
                nullCheck("weigh", userGoal.getWeigh()),
                nullCheck("sleepTime", userGoal.getSleepTime()),
                nullCheck("exerciseTime", userGoal.getExerciseTime()),
                nullCheck("waterIntake", userGoal.getWaterIntake()),
                userGoal.getId()
        );
    }

    private Object nullCheck(String column, Object object) {
        if((column == "sleepTime" || column == "exerciseTime") && object != null) {
            object = java.sql.Time.valueOf(object.toString());
        }
        else if((column == "weigh" || column == "waterIntake") && object != null) {
            if(Double.parseDouble(object.toString()) < 0.0) {
                throw new NegativeException("양수를 입력해주세요!");
            }
            object = Double.parseDouble(object.toString());
        }

        return object;
    }
}
