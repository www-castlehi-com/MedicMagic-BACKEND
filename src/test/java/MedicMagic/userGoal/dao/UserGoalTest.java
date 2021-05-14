package MedicMagic.userGoal.dao;

import MedicMagic.user.dao.DuplicateUserIdException;
import MedicMagic.userCalender.dao.NegativeException;
import MedicMagic.userGoal.domain.UserGoal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserGoalTest {
    @Autowired
    private UserGoalDao userGoalDao;
    @Autowired
    private DataSource dataSource;

    private UserGoal userGoal1;
    private UserGoal userGoal2;
    private HashMap<String, Object> goals;

    @Before
    public void setUp() throws Exception {
        userGoal1 = new UserGoal("gryffindor", 50.0, "08:00:00", null, 0.0);
        userGoal2 = new UserGoal("hufflepuff", 50.0, "08:00:00", null, 0.0);

        goals = new HashMap<String, Object>(){{
            put("weigh", 50.0);
            put("sleepTime", "08:00:00");
            put("exerciseTime", null);
            put("waterIntake", null);
        }};
    }

    @Test
    public void addAndGet() {
        userGoalDao.deleteAll();
        assertThat(userGoalDao.getCount(), is(0));

        userGoalDao.add(userGoal1.getId());
        assertThat(userGoalDao.getCount(), is(1));
        Iterator<Map.Entry<String, Object>> entries = goals.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            userGoalDao.update(entry.getKey(), entry.getValue(), userGoal1.getId());
        }
        UserGoal userGoalGet1 = userGoalDao.get(userGoal1.getId());
        checkSameUser(userGoalGet1, userGoal1);

        userGoalDao.add(userGoal2.getId());
        assertThat(userGoalDao.getCount(), is(2));
        entries = goals.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            userGoalDao.update(entry.getKey(), entry.getValue(), userGoal2.getId());
        }
        UserGoal userGoalGet2 = userGoalDao.get(userGoal2.getId());
        checkSameUser(userGoalGet2, userGoal2);
    }

    @Test(expected = DuplicateUserIdException.class)
    public void duplicateUserId() {
        userGoalDao.deleteAll();
        assertThat(userGoalDao.getCount(), is(0));

        userGoalDao.add(userGoal1.getId());
        userGoalDao.add(userGoal1.getId());
    }

    @Test(expected = NegativeException.class)
    public void negativeDouble() {
        userGoalDao.deleteAll();
        assertThat(userGoalDao.getCount(), is(0));

        userGoalDao.add(userGoal1.getId());
        userGoalDao.update("waterIntake", -2.5, userGoal1.getId());
    }

    @Test
    public void deleteEachId() {
        userGoalDao.deleteAll();
        assertThat(userGoalDao.getCount(), is(0));

        userGoalDao.add(userGoal1.getId());
        assertThat(userGoalDao.getCount(), is(1));
        Iterator<Map.Entry<String, Object>> entries = goals.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            userGoalDao.update(entry.getKey(), entry.getValue(), userGoal1.getId());
        }

        userGoalDao.add(userGoal2.getId());
        assertThat(userGoalDao.getCount(), is(2));
        entries = goals.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            userGoalDao.update(entry.getKey(), entry.getValue(), userGoal2.getId());
        }

        userGoalDao.deleteEachId(userGoal1.getId());
        assertThat(userGoalDao.getCount(), is(1));
    }

    private void checkSameUser(UserGoal userGoal1, UserGoal userGoal2) {
        assertThat(userGoal1.getId(), is(userGoal2.getId()));
        assertThat(userGoal1.getWeigh(), is(userGoal2.getWeigh()));
        assertThat(userGoal1.getSleepTime(), is(userGoal2.getSleepTime()));
        assertThat(userGoal1.getExerciseTime(), is(userGoal2.getExerciseTime()));
        assertThat(userGoal1.getWaterIntake(), is(userGoal2.getWaterIntake()));
    }
}
