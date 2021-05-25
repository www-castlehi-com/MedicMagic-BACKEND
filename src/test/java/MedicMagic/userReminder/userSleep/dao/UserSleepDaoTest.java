package MedicMagic.userReminder.userSleep.dao;

import MedicMagic.userReminder.userSleep.domain.UserSleep;
import MedicMagic.userReminder.userSleep.dto.UserSleepDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserSleepDaoTest {
    @Autowired
    UserSleepDao userSleepDao;
    @Autowired
    DataSource dataSource;

    UserSleep userSleep1;
    UserSleep userSleep2;

    UserSleepDto userSleepDto1;
    UserSleepDto userSleepDto2;

    UserSleepDto userSleepDto3;

    @Before
    public void setUp() throws Exception {
        userSleep1 = new UserSleep("gryffindor", 3.5);
        userSleep2 = new UserSleep("hufflepuff", 0.0);

        userSleepDto1 = new UserSleepDto(userSleep1);
        userSleepDto2 = new UserSleepDto(userSleep2);

        userSleepDto3 = new UserSleepDto("slyderin", "0.0");
    }

    @Test
    public void addAndGet() {
        userSleepDao.deleteAll();
        assertThat(userSleepDao.getCount(), is(0));

        userSleepDao.add(userSleepDto1);
        assertThat(userSleepDao.getCount(), is(1));

        userSleepDao.add(userSleepDto2);
        assertThat(userSleepDao.getCount(), is(2));

        checkSameUserSleep(userSleepDao.get(userSleepDto1.id), userSleepDto1);
        checkSameUserSleep(userSleepDao.get(userSleepDto2.id), userSleepDto2);
    }

    @Test
    public void stringTypeAddAndGet() {
        userSleepDao.deleteAll();
        assertThat(userSleepDao.getCount(), is(0));

        userSleepDao.add(userSleepDto3);
        assertThat(userSleepDao.getCount(), is(1));

        assertThat(userSleepDao.get(userSleepDto3.id).sleepGoal, is(0.0));
    }

    private void checkSameUserSleep(UserSleepDto userSleepDto1, UserSleepDto userSleepDto2) {
        assertThat(userSleepDto1.id, is(userSleepDto2.id));
        assertThat(userSleepDto1.sleepGoal, is(userSleepDto2.sleepGoal));
    }
}
