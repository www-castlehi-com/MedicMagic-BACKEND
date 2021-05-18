package MedicMagic.user.service;

import MedicMagic.user.domain.User;
import MedicMagic.userGoal.dao.UserGoalDao;
import MedicMagic.userReminder.dao.UserReminderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserService testUserService;
    @Autowired
    UserGoalDao userGoalDao;
    @Autowired
    UserReminderDao userReminderDao;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        testUserService.getAll();
    }

    @Test
    public void createGoalAndReminder() {
        userGoalDao.deleteAll();
        assertThat(userGoalDao.getCount(), is(0));
        userReminderDao.deleteAll();
        assertThat(userReminderDao.getCount(), is(0));

        User user = new User("hufflepuff", "박수민", "19011671", "2000-06-17", 22);

        userService.add(user);
        assertThat(userGoalDao.getCount(), is(1));
        assertThat(userReminderDao.getCount(), is(1));

        assertThat(userReminderDao.getAll().get(0).getId(), is(user.getId()));
        assertThat(userGoalDao.getAll().get(0).getId(), is(user.getId()));
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
