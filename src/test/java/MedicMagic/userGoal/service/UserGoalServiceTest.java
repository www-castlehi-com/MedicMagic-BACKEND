package MedicMagic.userGoal.service;

import MedicMagic.userGoal.domain.UserGoal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserGoalServiceTest {
    @Autowired
    UserGoalService testUserGoalService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        testUserGoalService.getAll();
    }

    static class TestUserGoalServiceImpl extends UserGoalServiceImpl {
        @Override
        public List<UserGoal> getAll() {
            for(UserGoal userGoal : super.getAll()) {
                super.update(userGoal);
            }
            return null;
        }
    }
}
