package MedicMagic.userMucus.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.exception.DuplicateDateException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userMucus.dto.UserMucusDto;
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
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserMucusDto> userMucusRowMapper =
            new RowMapper<UserMucusDto>() {
                @Override
                public UserMucusDto mapRow(ResultSet resultSet, int i) throws SQLException {
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
                    UserMucusDto userMucusDto = new UserMucusDto(userMucus);
                    return userMucusDto;
                }
            };

    @Override
    public void add(UserMucusDto userMucusDto) throws DuplicateDateException {
        try {
            this.jdbcTemplate.update(this.sqlService.getSql("userMucusAdd"),
                    userMucusDto.id,
                    userMucusDto.date,
                    userMucusDto.none,
                    userMucusDto.mottled,
                    userMucusDto.sticky,
                    userMucusDto.creamy,
                    userMucusDto.likeEggWhite,
                    userMucusDto.watery,
                    userMucusDto.abnormal
            );
        } catch(DuplicateKeyException e) {
            throw new DuplicateDateException(e);
        }
    }

    @Override
    public UserMucusDto get(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userMucusGet"),
                new Object[]{id, date}, this.userMucusRowMapper);
    }

    @Override
    public List<UserMucusDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userMucusGetAll"), this.userMucusRowMapper);
    }

    @Override
    public List<UserMucusDto> getEachId(String id) {
        return this.jdbcTemplate.query(this.sqlService.getSql("userMucusGetEachId"),
                new Object[]{id}, this.userMucusRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userMucusDeleteAll"));
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        this.jdbcTemplate.update(this.sqlService.getSql("userMucusDeleteEachIdAndDate"), id, date);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userMucusGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userMucusGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userMucusGetCountEachIdAndDate"), new Object[]{id, date}, Integer.class);
    }

    @Override
    public void update(UserMucusDto userMucusDto) {
        this.jdbcTemplate.update(
                this.sqlService.getSql("userMucusUpdate"),
                userMucusDto.none,
                userMucusDto.mottled,
                userMucusDto.sticky,
                userMucusDto.creamy,
                userMucusDto.likeEggWhite,
                userMucusDto.watery,
                userMucusDto.abnormal,
                userMucusDto.id,
                userMucusDto.date
        );
    }
}
