package MedicMagic.user.dao;

import MedicMagic.sqlService.SqlService;
import MedicMagic.user.DuplicateUserIdException;
import MedicMagic.user.NoUserException;
import MedicMagic.user.NullKeyException;
import MedicMagic.user.domain.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public void add(final User user) throws DuplicateUserIdException {
        try {
            nullCheck(user);
            this.jdbcTemplate.update(this.sqlService.getSql("userAdd"), user.getId(), user.getName(), user.getPassword(), java.sql.Date.valueOf(user.getBirthday()), user.getAge());
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserIdException("이미 가입된 아이디입니다", e);
        }
    }
    private void nullCheck(User user) throws NullKeyException {
        if(user.getId().equals("null") || user.getName().equals("null") || user.getPassword().equals("null") || user.getBirthday().equals("null") || user.getAge().equals("null")) {
            throw new NullKeyException("필수 입력 사항입니다");
        }
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
}
