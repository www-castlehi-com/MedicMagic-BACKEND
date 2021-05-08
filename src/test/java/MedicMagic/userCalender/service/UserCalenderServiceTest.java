package MedicMagic.userCalender.service;

import MedicMagic.userCalender.domain.UserCalender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserCalenderServiceTest {
    @Autowired
    UserCalenderService testUserCalenderService;

    List<UserCalender> userCalenders;

    @Before
    public void setUp() throws Exception {
        userCalenders = Arrays.asList(
                new UserCalender()
        );
    }

    @Test(expected = TransientDataAccessException.class)
    public void readOnlyTransactionAttribute() {
        testUserCalenderService.getAll();
    }

    static class TestUserCalenderServiceImpl extends UserCalenderServiceImpl {
        @Override
        public List<UserCalender> getAll() {
            for(UserCalender userCalender : super.getAll()) {
                super.update(userCalender, "weigh", userCalender.getWeigh());
            }
            return null;
        }
    }
}
