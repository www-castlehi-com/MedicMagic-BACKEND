package MedicMagic.userReminder.service;

import MedicMagic.userReminder.domain.UserReminder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserReminderServiceTest {
    @Autowired
    UserReminderService testUserReminderService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {testUserReminderService.getAll();}

    static class TestUserReminderServiceImpl extends UserReminderServiceImpl {
        @Override
        public List<UserReminder> getAll() {
            for(UserReminder userReminder : super.getAll()) {
                super.update("hospital", userReminder.isHospital(), userReminder.getId());
            }
            return null;
        }
    }
}
