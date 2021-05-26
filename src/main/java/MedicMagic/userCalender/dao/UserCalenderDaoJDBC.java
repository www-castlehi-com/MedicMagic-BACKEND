package MedicMagic.userCalender.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.exception.DuplicateDateException;
import MedicMagic.exception.NegativeException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
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

    private RowMapper<UserCalenderDto> userCalenderRowMapper =
            new RowMapper<UserCalenderDto>() {
                @Override
                public UserCalenderDto mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserCalender userCalender = new UserCalender();
                    userCalender.setId(resultSet.getString("id"));
                    userCalender.setDate(resultSet.getString("date"));
                    userCalender.setSleepTime(resultSet.getString("sleepTime"));
                    userCalender.setExerciseTime(resultSet.getString("exerciseTime"));
                    userCalender.setWaterIntake(resultSet.getInt("waterIntake"));
                    userCalender.setStartDay(resultSet.getString("startDay"));
                    userCalender.setEndDay(resultSet.getString("endDay"));
                    userCalender.setSymptom(resultSet.getBoolean("symptom"));
                    userCalender.setMucus(resultSet.getBoolean("mucus"));
                    UserCalenderDto userCalenderDto = new UserCalenderDto(userCalender);
                    return userCalenderDto;
                }
            };

    @Override
    public void add(UserCalenderDto userCalenderDto) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update(this.sqlService.getSql("userCalenderAdd"),
                    userCalenderDto.id,
                    userCalenderDto.date,
                    userCalenderDto.sleepTime,
                    userCalenderDto.exerciseTime,
                    userCalenderDto.waterIntake,
                    nullCheck("startDay", userCalenderDto.startDay),
                    nullCheck("endDay", userCalenderDto.endDay),
                    userCalenderDto.symptom,
                    userCalenderDto.mucus);
        } catch(DuplicateKeyException e) {
            throw new DuplicateDateException("중복된 날짜입니다", e);
        }
    }

    @Override
    public UserCalenderDto get(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGet"),
                new Object[]{id, date}, this.userCalenderRowMapper);
    }

    @Override
    public List<UserCalenderDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userCalenderGetAll"), this.userCalenderRowMapper);
    }

    @Override
    public List<UserCalenderDto> getEachId(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userCalenderGetEachId"),
                new Object[]{id}, this.userCalenderRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userCalenderDeleteAll"));
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        this.jdbcTemplate.update(this.sqlService.getSql("userCalenderDeleteEachIdAndDate"), id, date);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userCalenderGetCountEachIdAndDate"), new Object[]{id, date}, Integer.class);
    }

    @Override
    public void update(UserCalenderDto userCalenderDto) {
        this.jdbcTemplate.update(
                this.sqlService.getSql("userCalenderUpdate"),
                userCalenderDto.sleepTime,
                userCalenderDto.exerciseTime,
                userCalenderDto.waterIntake,
                nullCheck("startDay", userCalenderDto.startDay),
                nullCheck("endDay", userCalenderDto.endDay),
                userCalenderDto.symptom,
                userCalenderDto.mucus,
                userCalenderDto.id,
                userCalenderDto.date
        );
    }

    private Object nullCheck(String column, Object object) {
        if(column == "startDay" && object != null) {
            object = java.sql.Date.valueOf(object.toString());
        }
        else if(column == "endDay" && object != null) {
            object = java.sql.Date.valueOf(object.toString());
        }

        return object;
    }
}
