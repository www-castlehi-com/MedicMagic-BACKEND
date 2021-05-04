package MedicMagic.user.dao;

import MedicMagic.user.domain.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBC implements UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

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
    public void add(final User user) throws DuplicateUserIdException {
        try {
            this.jdbcTemplate.update("INSERT INTO user(id, name, password, birthday, age) values(?, ?, ?, ?, ?)", user.getId(), user.getName(), user.getPassword(), java.sql.Date.valueOf(user.getBirthday()), user.getAge());
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserIdException(e);
        }
    }

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?",
                new Object[]{id}, this.userMapper);
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM user ORDER BY id", this.userMapper);
    }

    @Override
    public void update(User user1) {
        this.jdbcTemplate.update(
                "update user set name = ?, password = ?, birthday = ?, age = ? where id = ?", user1.getName(), user1.getPassword(), user1.getBirthday(), user1.getAge(), user1.getId()
        );
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update("DELETE FROM user");
    }

    @Override
    public int getCount() {
        return this.jdbcTemplate.queryForInt("SELECT COUNT(*) FROM user");
    }
}
