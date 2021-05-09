package MedicMagic.user.service;

import MedicMagic.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService testUserService;

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
