package MedicMagic.userSymptom.dao;

import MedicMagic.userCalender.dao.DuplicateDateException;
import MedicMagic.userCalender.dao.UserCalenderDao;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSymptomDaoJDBC implements UserSymptomDao{
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    private RowMapper<UserSymptom> userSymptomRowMapper =
            new RowMapper<UserSymptom>() {
                @Override
                public UserSymptom mapRow(ResultSet resultSet, int i) throws SQLException {
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

                    return userSymptom;
                }
            };

    @Override
    public void add(UserSymptom userSymptom) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update("INSERT INTO userSymptom(id, date, none, cramps, breastTenderness, headache, acne, lumbago, nausea, fatigue, abdominalBloating, desires, insomnia, constipation, diarrhea) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", userSymptom.getId(), java.sql.Date.valueOf(userSymptom.getDate()), userSymptom.isNone(), userSymptom.isCramps(), userSymptom.isBreastTenderness(), userSymptom.isHeadache(), userSymptom.isAcne(), userSymptom.isLumbago(), userSymptom.isLumbago(), userSymptom.isFatigue(), userSymptom.isAbdominalBloating(), userSymptom.isDesires(), userSymptom.isInsomnia(), userSymptom.isConstipation(), userSymptom.isDiarrhea());
        } catch(DuplicateKeyException e) {
            throw new DuplicateDateException(e);
        }
    }

    @Override
    public UserSymptom get(String id, String date) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM userSymptom WHERE id = ? AND date = ?",
                new Object[]{id, date}, this.userSymptomRowMapper);
    }

    @Override
    public List<UserSymptom> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM userSymptom ORDER BY id, date DESC", this.userSymptomRowMapper);
    }

    @Override
    public List<UserSymptom> getEachId(String id) {
        return this.jdbcTemplate.query("SELECT * FROM userSymptom WHERE id = ?",
                new Object[]{id}, this.userSymptomRowMapper);
    }

    @Override
    public List<String> getSymptomTrue(UserSymptom userSymptom) {
        List<String> symptoms = new ArrayList<>();

        symptoms.add(userSymptom.getId());
        symptoms.add(userSymptom.getDate());

        if(userSymptom.isNone()) {
            symptoms.add("없음");
        }

        if(userSymptom.isCramps()){
            symptoms.add("생리통");
        }

        if(userSymptom.isBreastTenderness()) {
            symptoms.add("유방의 압통");
        }

        if(userSymptom.isHeadache()) {
            symptoms.add("두통");
        }

        if(userSymptom.isAcne()) {
            symptoms.add("여드름");
        }

        if(userSymptom.isLumbago()) {
            symptoms.add("요통");
        }

        if(userSymptom.isFatigue()) {
            symptoms.add("피로감");
        }

        if(userSymptom.isAbdominalBloating()) {
            symptoms.add("복부 팽만감");
        }

        if(userSymptom.isDesires()) {
            symptoms.add("갈망감");
        }

        if(userSymptom.isInsomnia()) {
            symptoms.add("불면증");
        }

        if(userSymptom.isConstipation()) {
            symptoms.add("변비");
        }

        if(userSymptom.isDiarrhea()) {
            symptoms.add("설사");
        }

        return symptoms;
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM userSymptom");
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userSymptom");
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userSymptom GROUP BY id = ?", id);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userSymptom WHERE date = ? GROUP BY id = ?", date, id);
    }

    @Override
    public void update(UserSymptom userSymptom, String column, boolean object) {
        this.jdbcTemplate.update(
                "UPDATE userSymptom SET " + column +"= ? WHERE id = ? AND date = ?", object, userSymptom.getId(), userSymptom.getDate()
        );
    }

    @Override
    public void updateUserCalenderIfSymptomIsFalse(UserSymptom userSymptom, UserCalender userCalender) {
        if(this.getSymptomTrue(userSymptom).size() == 2) {
            userCalender.setSymptom(false);
            this.jdbcTemplate.update("DELETE FROM userSymptom WHERE id = ? AND date = ?", userCalender.getId(), userCalender.getDate());
        }

    }
}
