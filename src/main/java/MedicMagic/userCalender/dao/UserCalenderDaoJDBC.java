package MedicMagic.userCalender.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userCalender.domain.UserCalender;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserCalenderDaoJDBC implements UserCalenderDao {
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserCalender> userCalenderRowMapper =
            new RowMapper<UserCalender>() {
                @Override
                public UserCalender mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserCalender userCalender = new UserCalender();
                    userCalender.setId(resultSet.getString("id"));
                    userCalender.setDate(resultSet.getString("date"));
                    userCalender.setWeigh(resultSet.getDouble("weigh"));
                    userCalender.setSleepTime(resultSet.getString("sleepTime"));
                    userCalender.setExerciseTime(resultSet.getString("exerciseTime"));
                    userCalender.setWaterIntake(resultSet.getDouble("waterIntake"));
                    userCalender.setStartDay(resultSet.getString("startDay"));
                    userCalender.setEndDay(resultSet.getString("endDay"));
                    userCalender.setEmotion(resultSet.getString("emotion"));
                    userCalender.setSymptom(resultSet.getBoolean("symptom"));
                    userCalender.setMucus(resultSet.getBoolean("mucus"));

                    return userCalender;
                }
            };

    @Override
    public void add(UserCalender userCalender) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update(this.sqlService.getSql("userCalenderAdd"), userCalender.getId(), userCalender.getDate(), userCalender.getEmotion(), userCalender.isSymptom(), userCalender.isMucus());
            nullCheck(userCalender);
        } catch(DuplicateKeyException e) {
            throw new DuplicateDateException(e);
        }
    }
    private void nullCheck(UserCalender userCalender) {
        if(userCalender.getWeigh() != null) {
            userCalender.setWeigh(negativeInspection(userCalender.getWeigh()));
            update(userCalender, "weigh", userCalender.getWeigh());
        }

        if(userCalender.getSleepTime() != null) {
            update(userCalender, "sleepTime", java.sql.Time.valueOf(userCalender.getSleepTime()));
        }

        if(userCalender.getExerciseTime() != null) {
            update(userCalender, "exerciseTime", java.sql.Time.valueOf(userCalender.getExerciseTime()));
        }

        if(userCalender.getWaterIntake() != null) {
            userCalender.setWaterIntake(negativeInspection(userCalender.getWaterIntake()));
            update(userCalender, "waterIntake", userCalender.getWaterIntake());
        }

        if(userCalender.getStartDay() != null) {
            update(userCalender, "startDay", java.sql.Date.valueOf(userCalender.getStartDay()));
        }

        if(userCalender.getEndDay() != null) {
            update(userCalender, "endDay", java.sql.Date.valueOf(userCalender.getEndDay()));
        }
    }

    @Override
    public UserCalender get(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGet"),
                new Object[]{id, date}, this.userCalenderRowMapper);
    }

    @Override
    public List<UserCalender> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userCalenderGetAll"), this.userCalenderRowMapper);
    }

    @Override
    public List<UserCalender> getEachId(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userCalenderGetEachId"),
                new Object[]{id}, this.userCalenderRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userCalenderDeleteAll"));
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForInt(this.sqlService.getSql("userCalenderGetCount"));
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForInt(this.sqlService.getSql("userCalenderGetCountEachId"), id);
    }

    @Override
    public void update(UserCalender userCalender, String column, Object object) {
        this.jdbcTemplate.update(
                "update userCalender SET "+ column +"= ? WHERE id = ? AND date = ?",
                object, userCalender.getId(), userCalender.getDate()
        );
    }

    private Double negativeInspection(Double object) throws NegativeException {

        if(object < 0.0) { throw new NegativeException("양수를 입력해주세요!"); }

        return object;
    };
}
