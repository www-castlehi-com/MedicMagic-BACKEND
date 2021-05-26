package MedicMagic.userSymptom.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.exception.DuplicateDateException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;
import MedicMagic.userSymptom.dto.UserSymptomDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSymptomDaoJDBC implements UserSymptomDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserSymptomDto> userSymptomRowMapper =
            new RowMapper<UserSymptomDto>() {
                @Override
                public UserSymptomDto mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserSymptom userSymptom = new UserSymptom();
                    userSymptom.setId(resultSet.getString("id"));
                    userSymptom.setDate(resultSet.getString("date"));
                    userSymptom.setNone(resultSet.getBoolean("none"));
                    userSymptom.setCramps(resultSet.getBoolean("cramps"));
                    userSymptom.setBreastTenderness(resultSet.getBoolean("breastTenderness"));
                    userSymptom.setHeadache(resultSet.getBoolean("headache"));
                    userSymptom.setAcne(resultSet.getBoolean("acne"));
                    userSymptom.setLumbago(resultSet.getBoolean("lumbago"));
                    userSymptom.setNausea(resultSet.getBoolean("nausea"));
                    userSymptom.setFatigue(resultSet.getBoolean("fatigue"));
                    userSymptom.setAbdominalBloating(resultSet.getBoolean("abdominalBloating"));
                    userSymptom.setDesires(resultSet.getBoolean("desires"));
                    userSymptom.setInsomnia(resultSet.getBoolean("insomnia"));
                    userSymptom.setConstipation(resultSet.getBoolean("constipation"));
                    userSymptom.setDiarrhea(resultSet.getBoolean("diarrhea"));
                    UserSymptomDto userSymptomDto = new UserSymptomDto(userSymptom);
                    return userSymptomDto;
                }
            };

    @Override
    public void add(UserSymptomDto userSymptomDto) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update(this.sqlService.getSql("userSymptomAdd"),
                    userSymptomDto.id,
                    java.sql.Date.valueOf(userSymptomDto.date),
                    userSymptomDto.none,
                    userSymptomDto.cramps,
                    userSymptomDto.breastTenderness,
                    userSymptomDto.headache,
                    userSymptomDto.acne,
                    userSymptomDto.lumbago,
                    userSymptomDto.nausea,
                    userSymptomDto.fatigue,
                    userSymptomDto.abdominalBloating,
                    userSymptomDto.desires,
                    userSymptomDto.insomnia,
                    userSymptomDto.constipation,
                    userSymptomDto.diarrhea);
        } catch(DuplicateKeyException e) {
            throw new DuplicateDateException(e);
        }
    }

    @Override
    public UserSymptomDto get(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userSymptomGet"),
                new Object[]{id, date}, this.userSymptomRowMapper);
    }

    @Override
    public List<UserSymptomDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userSymptomGetAll"), this.userSymptomRowMapper);
    }

    @Override
    public List<UserSymptomDto> getEachId(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userSymptomGetEachId"),
                new Object[]{id}, this.userSymptomRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userSymptomDeleteAll"));
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        this.jdbcTemplate.update(this.sqlService.getSql("userSymptomDeleteEachIdAndDate"), id, date);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userSymptomGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userSymptomGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userSymptomGetCountEachIdAndDate"), new Object[]{id, date}, Integer.class);
    }

    @Override
    public void update(UserSymptomDto userSymptomDto) {
        this.jdbcTemplate.update(
                this.sqlService.getSql("userSymptomUpdate"),
                userSymptomDto.none,
                userSymptomDto.cramps,
                userSymptomDto.breastTenderness,
                userSymptomDto.headache,
                userSymptomDto.acne,
                userSymptomDto.lumbago,
                userSymptomDto.nausea,
                userSymptomDto.fatigue,
                userSymptomDto.abdominalBloating,
                userSymptomDto.desires,
                userSymptomDto.insomnia,
                userSymptomDto.constipation,
                userSymptomDto.diarrhea,
                userSymptomDto.id,
                userSymptomDto.date
        );
    }
}
