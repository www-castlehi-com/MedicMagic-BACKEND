package MedicMagic.user.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.exception.NoUserException;
import MedicMagic.exception.NullKeyException;
import MedicMagic.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBC implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private SqlService sqlService;

    public void setJdbcTemplate(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private RowMapper<User> userMapper =
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {

                    User user = new User();
                    user.setId(resultSet.getString("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setBirthday(resultSet.getString("birthday"));
                    user.setAge(resultSet.getInt("age"));

                    return user;
                }
            };

    @Override
    public void add(final User user) {
        this.jdbcTemplate.update(this.sqlService.getSql("userAdd"), user.getId(), user.getName(), user.getPassword(), java.sql.Date.valueOf(user.getBirthday()), user.getAge());
    }

    @Override
    public User get(String id) throws NoUserException, NullKeyException {
        try {
            return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGet"),
                    new Object[]{id}, this.userMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new NoUserException("회원이 존재하지 않습니다");
        }
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(this.sqlService.getSql("userGetAll"), this.userMapper);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update(this.sqlService.getSql("userUpdate"), user.getName(), user.getPassword(), user.getBirthday(), user.getAge(), user.getId()
        );
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(this.sqlService.getSql("userDeleteAll"));
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGetCount"), Integer.class);
    }

    @Override
    public int getCountEachId(String id) {
        return this.jdbcTemplate.queryForObject(this.sqlService.getSql("userGetCountEachId"), new Object[]{id}, Integer.class);
    }
}
