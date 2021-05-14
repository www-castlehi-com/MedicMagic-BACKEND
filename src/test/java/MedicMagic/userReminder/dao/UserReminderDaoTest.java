package MedicMagic.userReminder.dao;

import MedicMagic.user.dao.DuplicateUserIdException;
import MedicMagic.userReminder.domain.UserReminder;
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
public class UserReminderDaoTest {
    @Autowired
    private UserReminderDao userReminderDao;
    @Autowired
    private DataSource dataSource;

    private UserReminder userReminder1;
    private UserReminder userReminder2;
    private HashMap<String, Object> reminders1;
    private HashMap<String, Object> reminders2;

    @Before
    public void setUp() throws Exception {
        userReminder1 = new UserReminder("gryffindor", true, 15, "08:00:00", false, 3, true, false, true, 3);
        userReminder2 = new UserReminder("hufflepuff", true, 21, "07:00:00", true, 3, false, true, true, 1);

        reminders1 = new HashMap<String, Object>(){{
            put("birthControlPills", true);
            put("beforeBirthControlPills", 15);
            put("birthControlPillsTime", null);
            put("physiology", false);
            put("beforePhysiology", null);
            put("sleepTimeGoal", true);
            put("exerciseTimeGoal", false);
            put("hospital", true);
            put("hospitalDate", 3);
        }};
        reminders2 = new HashMap<String, Object>() {{
            put("birthControlPills", true);
            put("beforeBirthControlPills", null);
            put("birthControlPillsTime", "07:00:00");
            put("physiology", true);
            put("beforePhysiology", null);
            put("sleepTimeGoal", false);
            put("exerciseTimeGoal", true);
            put("hospital", true);
            put("hospitalDate", null);
        }};
    }

    @Test
    public void addAndGet() {
        userReminderDao.deleteAll();
        assertThat(userReminderDao.getCount(), is(0));

        userReminderDao.add(userReminder1.getId());
        assertThat(userReminderDao.getCount(), is(1));

        Iterator<Map.Entry<String, Object>> entries = reminders1.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String,Object> entry = entries.next();
            userReminderDao.update(entry.getKey(), entry.getValue(), userReminder1.getId());
        }
        UserReminder userReminderGet1 = userReminderDao.get(userReminder1.getId());
        checkSameUser(userReminderGet1, userReminder1);

        userReminderDao.add(userReminder2.getId());
        assertThat(userReminderDao.getCount(), is(2));

        entries = reminders2.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String,Object> entry = entries.next();
            userReminderDao.update(entry.getKey(), entry.getValue(), userReminder2.getId());
        }
        UserReminder userReminderGet2 = userReminderDao.get(userReminder2.getId());
        checkSameUser(userReminderGet2, userReminder2);
    }

    @Test(expected = DuplicateUserIdException.class)
    public void duplicateUserId() {
        userReminderDao.deleteAll();
        assertThat(userReminderDao.getCount(), is(0));

        userReminderDao.add(userReminder1.getId());
        userReminderDao.add(userReminder1.getId());
        assertThat(userReminderDao.getCount(), is(1));
    }

    @Test
    public void deleteEachId() {
        userReminderDao.deleteAll();
        assertThat(userReminderDao.getCount(), is(0));

        userReminderDao.add(userReminder1.getId());
        assertThat(userReminderDao.getCount(), is(1));
        Iterator<Map.Entry<String, Object>> entries = reminders1.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String,Object> entry = entries.next();
            userReminderDao.update(entry.getKey(), entry.getValue(), userReminder1.getId());
        }

        userReminderDao.add(userReminder2.getId());
        assertThat(userReminderDao.getCount(), is(2));
        entries = reminders2.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<String,Object> entry = entries.next();
            userReminderDao.update(entry.getKey(), entry.getValue(), userReminder2.getId());
        }

        userReminderDao.deleteEachId(userReminder1.getId());
        assertThat(userReminderDao.getCount(), is(1));
    }

    private void checkSameUser(UserReminder userReminder1, UserReminder userReminder2) {
        assertThat(userReminder1.getId(), is(userReminder2.getId()));
        assertThat(userReminder1.isBirthControlPills(), is(userReminder2.isBirthControlPills()));
        assertThat(userReminder1.getBeforeBirthControlPills(), is(userReminder2.getBeforeBirthControlPills()));;
        assertThat(userReminder1.getBirthControlPillsTime(), is(userReminder2.getBirthControlPillsTime()));
        assertThat(userReminder1.isPhysiology(), is(userReminder2.isPhysiology()));
        assertThat(userReminder1.getBeforePhysiology(), is(userReminder2.getBeforePhysiology()));
        assertThat(userReminder1.isSleepTimeGoal(), is(userReminder2.isSleepTimeGoal()));
        assertThat(userReminder1.isExerciseTimeGoal(), is(userReminder2.isExerciseTimeGoal()));
        assertThat(userReminder1.isHospital(), is(userReminder2.isHospital()));
        assertThat(userReminder1.getHospitalDate(), is(userReminder2.getHospitalDate()));
    }
}
