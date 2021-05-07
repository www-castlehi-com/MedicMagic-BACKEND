package MedicMagic.userCalender.dao;

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

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

                    return userCalender;
                }
            };

    @Override
    public void add(UserCalender userCalender) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update("INSERT INTO userCalender(id, date, emotion, symptom) VALUES (?, ?, ?, ?)", userCalender.getId(), userCalender.getDate(), userCalender.getEmotion(), userCalender.isSymptom());
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
        return this.jdbcTemplate.queryForObject("SELECT * FROM userCalender WHERE id = ? AND date = ?",
                new Object[]{id, date}, this.userCalenderRowMapper);
    }

    @Override
    public List<UserCalender> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM userCalender ORDER BY id, date DESC", this.userCalenderRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM userCalender");
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userCalender");
    }

    @Override
    public int eachIdGetCount() {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userCalender GROUP BY id");
    }

    @Override
    public void update(UserCalender userCalender, String column, Object object) {
        this.jdbcTemplate.update(
                "update userCalender SET "+ column +"= ? WHERE id = ? AND date = ?", object, userCalender.getId(), userCalender.getDate()
        );
    }

    private Double negativeInspection(Double object) throws NegativeException {

        if(object < 0.0) { throw new NegativeException("양수를 입력해주세요!"); }

        return object;
    };
}
