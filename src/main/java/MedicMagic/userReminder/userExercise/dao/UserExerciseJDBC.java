package MedicMagic.userReminder.userExercise.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.userReminder.userExercise.domain.UserExercise;
import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserExerciseJDBC implements UserExerciseDao{
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<UserExerciseDto> userExerciseDtoRowMapper = new RowMapper<UserExerciseDto>() {
        @Override
        public UserExerciseDto mapRow(ResultSet resultSet, int i) throws SQLException {
            UserExercise userExercise = new UserExercise();
            userExercise.setId(resultSet.getString("id"));
            userExercise.setExerciseHour(resultSet.getDouble("exerciseHour"));
            UserExerciseDto userExerciseDto = new UserExerciseDto(userExercise);
            return userExerciseDto;
        }
    };

    @Override
    public void add(UserExerciseDto userExerciseDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userExerciseAdd"), userExerciseDto.id, userExerciseDto.exerciseHour);
    }

    @Override
    public UserExerciseDto get(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userExerciseGet"), new Object[]{id}, this.userExerciseDtoRowMapper);
    }

    @Override
    public List<UserExerciseDto> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userExerciseGetAll"), this.userExerciseDtoRowMapper);
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userExerciseDeleteAll"));
    }

    @Override
    public void deleteEachId(String id) {
        this.jdbcTemplate.update(this.sqlService.getSql("userExerciseDeleteEachId"), id);
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userExerciseGetCount"),Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userExerciseGetCountEachId"), new Object[]{id}, Integer.class);
    }

    @Override
    public void update(UserExerciseDto userExerciseDto) {
        this.jdbcTemplate.update(this.sqlService.getSql("userExerciseUpdate"), userExerciseDto.exerciseHour, userExerciseDto.id);
    }
}
