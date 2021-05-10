package MedicMagic.userMucus.dao;

import MedicMagic.userCalender.dao.DuplicateDateException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userSymptom.domain.UserSymptom;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMucusDaoJDBC implements UserMucusDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<UserMucus> userMucusRowMapper =
            new RowMapper<UserMucus>() {
                @Override
                public UserMucus mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserMucus userMucus = new UserMucus();
                    userMucus.setId(resultSet.getString("id"));
                    userMucus.setDate(resultSet.getString("date"));
                    userMucus.setNone(resultSet.getBoolean("none"));
                    userMucus.setMottled(resultSet.getBoolean("mottled"));
                    userMucus.setSticky(resultSet.getBoolean("sticky"));
                    userMucus.setCreamy(resultSet.getBoolean("creamy"));
                    userMucus.setLikeEggWhite(resultSet.getBoolean("likeEggWhite"));
                    userMucus.setWatery(resultSet.getBoolean("watery"));
                    userMucus.setAbnormal(resultSet.getBoolean("abnormal"));

                    return userMucus;
                }
            };

    @Override
    public void add(UserMucus userMucus) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update("INSERT INTO userMucus(id, date, none, mottled, sticky, creamy, likeEggwhite, watery, abnormal) VALUES (?,?,?,?,?,?,?,?,?)", userMucus.getId(), userMucus.getDate(), userMucus.isNone(), userMucus.isMottled(), userMucus.isSticky(), userMucus.isCreamy(), userMucus.isLikeEggWhite(), userMucus.isWatery(), userMucus.isAbnormal());
        } catch(DuplicateKeyException e) {
            throw new DuplicateDateException(e);
        }
    }

    @Override
    public UserMucus get(String id, String date) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM userMucus WHERE id = ? AND date = ?",
                new Object[]{id, date}, this.userMucusRowMapper);
    }

    @Override
    public List<UserMucus> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM userMucus ORDER BY id, date DESC", this.userMucusRowMapper);
    }

    @Override
    public List<UserMucus> getEachId(String id) {
        return this.jdbcTemplate.query("SELECT * FROM userMucus WHERE id = ?",
                new Object[]{id}, this.userMucusRowMapper);
    }

    @Override
    public List<String> getMucusTrue(UserMucus userMucus) {
        List<String> mucus = new ArrayList<>();

        mucus.add(userMucus.getId());
        mucus.add(userMucus.getDate());

        if(userMucus.isNone()) {
            mucus.add("없음");
        }

        if(userMucus.isMottled()) {
            mucus.add("얼룩덜룩함");
        }

        if(userMucus.isSticky()) {
            mucus.add("끈적함");
        }

        if(userMucus.isCreamy()) {
            mucus.add("크림같음");
        }

        if(userMucus.isLikeEggWhite()) {
            mucus.add("계란 흰자 같음");
        }

        if(userMucus.isWatery()) {
            mucus.add("수분이 많음");
        }

        if(userMucus.isAbnormal()) {
            mucus.add("비정상적임");
        }

        return mucus;
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM userMucus");
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userMucus");
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userMucus GROUP BY id = ?", id);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM userMucus WHERE date = ? GROUP BY id = ?", date, id);
    }

    @Override
    public void update(UserMucus userMucus, String column, boolean object) {
        this.jdbcTemplate.update(
                "UPDATE userMucus SET " + column + "= ? WHERE id = ? AND date = ?", object, userMucus.getId(), userMucus.getDate()
        );
    }

    @Override
    public void updateUserCalenderIfMucusIsFalse(UserMucus userMucus, UserCalender userCalender) {
        if(this.getMucusTrue(userMucus).size() == 2) {
            userCalender.setMucus(false);
            this.jdbcTemplate.update("DELETE FROM userMucus WHERE id = ? AND date = ?", userCalender.getId(), userCalender.getDate());
        }
    }
}
