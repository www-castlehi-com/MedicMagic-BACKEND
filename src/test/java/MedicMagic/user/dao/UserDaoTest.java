package MedicMagic.user.dao;

import MedicMagic.user.DuplicateUserIdException;
import MedicMagic.user.NullKeyException;
import MedicMagic.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private UserDao dao;
    @Autowired
    private DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @Before
    public void setUp() {
        this.user1 = new User("gryffindor", "김수라", "19011655", "2000-05-21", 22);
        this.user2 = new User("hufflepuff", "김한슬", "19011637", "1999-05-29", 23);
        this.user3 = new User("ravenclaw", "박수민", "19011671", "2000-06-17", 22);
        this.user4 = new User("slytherin", "신애림", "19011668", "2000-04-09", 22);
    }

    @Test
    public void addAndGet() {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = dao.get(user2.getId());
        checkSameUser(userget2, user2);

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
        User userget3 = dao.get(user3.getId());
        checkSameUser(userget3, user3);

        dao.add(user4);
        assertThat(dao.getCount(), is(4));
        User userget4 = dao.get(user4.getId());
        checkSameUser(userget4, user4);
    }

    @Test
    public void count() {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));

        dao.add(user4);
        assertThat(dao.getCount(), is(4));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }

    @Test
    public void getAll() {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0.size(), is(0));

        dao.add(user3);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));
        checkSameUser(user3, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user2, users2.get(0));

        dao.add(user1);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user1, users3.get(0));

        dao.add(user4);
        List<User> users4 = dao.getAll();
        assertThat(users4.size(), is(4));
        checkSameUser(user1, users4.get(0));
    }

    @Test
    public void update(){
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        user1.setName("앪술랑뭉팕");
        user1.setPassword("wingardium");
        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);
        User user2same = dao.get(user2.getId());
        checkSameUser(user2, user2same);
    }

    @Test(expected = DuplicateUserIdException.class)
    public void duplicateKey() {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user1);
    }

    @Test(expected = NullKeyException.class)
    public void nullKey() {
        dao.deleteAll();

        User mockUser = new User(null, null, null, null, null);
        dao.add(mockUser);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getBirthday(), is(user2.getBirthday()));
        assertThat(user1.getAge(), is(user2.getAge()));
    }
}
