package MedicMagic.user.service;

import MedicMagic.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService testUserService;

    List<User> users;

    @Before
    public void setUp() throws Exception {
        users = Arrays.asList(
                new User("gryffindor", "김수라", "19011655", "2000-05-21", 22),
                new User("hufflepuff", "김한슬", "19011637", "1999-05-29", 23),
                new User("ravenclaw", "박수민", "19011671", "2000-06-17", 22),
                new User("slytherin", "신애림", "19011668", "2000-04-09", 22)
        );
    }

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        testUserService.getAll();
    }

    static class TestUserServiceImpl extends UserServiceImpl {

        @Override
        public List<User> getAll() {
            for(User user : super.getAll()) {
                super.update(user);
            }
            return null;
        }
    }
}
