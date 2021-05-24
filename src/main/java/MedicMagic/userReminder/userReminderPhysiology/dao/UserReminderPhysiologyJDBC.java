package MedicMagic.userReminder.userReminderPhysiology.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userReminder.userReminderPhysiology.domain.UserReminderPhysiology;
import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserReminderPhysiologyJDBC implements UserReminderPhysiologyDao {
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserReminderPhysiologyDto> userPhysiologyDtoRowMapper = new RowMapper<UserReminderPhysiologyDto>() {
        @Override
        public UserReminderPhysiologyDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserReminderPhysiology userPhysiology = new UserReminderPhysiology();
            userPhysiology.setId(resultSet.getString("id"));
            userPhysiology.setPhysiologyTime(resultSet.getString("physiologyTime"));
            UserReminderPhysiologyDto userPhysiologyDto = new UserReminderPhysiologyDto(userPhysiology);
            return userPhysiologyDto;
        }
    };

    @Override
    public void add(UserReminderPhysiologyDto userPhysiologyDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderPhysiologyAdd"), userPhysiologyDto.id, userPhysiologyDto.physiologyTime);
    }

    @Override
    public UserReminderPhysiologyDto get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderPhysiologyGet"), new Object[]{id}, this.userPhysiologyDtoRowMapper);
    }

    @Override
    public List<UserReminderPhysiologyDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userReminderPhysiologyGetAll"), this.userPhysiologyDtoRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderPhysiologyDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderPhysiologyDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderPhysiologyGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userReminderPhysiologyGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserReminderPhysiologyDto userPhysiologyDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userReminderPhysiologyUpdate"), userPhysiologyDto.physiologyTime, userPhysiologyDto.id);
    }
}
