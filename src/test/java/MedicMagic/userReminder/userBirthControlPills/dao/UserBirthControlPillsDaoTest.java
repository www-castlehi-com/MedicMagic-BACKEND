package MedicMagic.userReminder.userBirthControlPills.dao;

import MedicMagic.userReminder.userBirthControlPills.domain.UserBirthControlPills;
import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserBirthControlPillsDaoTest {
    @Autowired
    UserBirthControlPillsDao userBirthControlPillsDao;
    @Autowired
    DataSource dataSource;

    UserBirthControlPills userBirthControlPills1;
    UserBirthControlPills userBirthControlPills2;

    UserBirthControlPillsDto userBirthControlPillsDto1;
    UserBirthControlPillsDto userBirthControlPillsDto2;
    UserBirthControlPillsDto userBirthControlPillsDto3;
    UserBirthControlPillsDto userBirthControlPillsDto4;

    @Before
    public void setUp() throws Exception {
        userBirthControlPills1 = new UserBirthControlPills("gryffindor", null, null, 14);
        userBirthControlPills2 = new UserBirthControlPills("ravenclaw", "18:30:00", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);

        userBirthControlPillsDto1 = new UserBirthControlPillsDto(userBirthControlPills1);
        userBirthControlPillsDto2 = new UserBirthControlPillsDto(userBirthControlPills2);

        userBirthControlPillsDto3 = new UserBirthControlPillsDto("gryffindor", "null", "null", "14");
        userBirthControlPillsDto4 = new UserBirthControlPillsDto("ravenclaw", "18:30:00", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "null");
    }

    @Test
    public void addAndGet() {
        userBirthControlPillsDao.deleteAll();
        assertThat(userBirthControlPillsDao.getCount(), is(0));

        userBirthControlPillsDao.add(userBirthControlPillsDto1);
        assertThat(userBirthControlPillsDao.getCount(), is(1));

        userBirthControlPillsDao.add(userBirthControlPillsDto2);
        assertThat(userBirthControlPillsDao.getCount(), is(2));

        checkSameUserBirthControlPills(userBirthControlPillsDto1, userBirthControlPillsDao.get(userBirthControlPillsDto1.id));
        checkSameUserBirthControlPills(userBirthControlPillsDto2, userBirthControlPillsDao.get(userBirthControlPillsDto2.id));

        assertThat(userBirthControlPillsDao.get(userBirthControlPillsDto1.id).pillsDate, is(nullValue()));
        assertThat(userBirthControlPillsDao.get(userBirthControlPillsDto1.id).pillsTime, is(nullValue()));
        assertThat(userBirthControlPillsDao.get(userBirthControlPillsDto2.id).days, is(21));
    }

    @Test
    public void typeIsStringAddAndGet() {
        userBirthControlPillsDao.deleteAll();
        assertThat(userBirthControlPillsDao.getCount(), is(0));

        userBirthControlPillsDao.add(userBirthControlPillsDto3);
        assertThat(userBirthControlPillsDao.getCount(), is(1));

        userBirthControlPillsDao.add(userBirthControlPillsDto4);
        assertThat(userBirthControlPillsDao.getCount(), is(2));

        assertThat(userBirthControlPillsDao.get(userBirthControlPillsDto3.id).pillsDate, is(nullValue()));
        assertThat(userBirthControlPillsDao.get(userBirthControlPillsDto3.id).pillsTime, is(nullValue()));
        assertThat(userBirthControlPillsDao.get(userBirthControlPillsDto4.id).days, is(21));
    }

    private void checkSameUserBirthControlPills(UserBirthControlPillsDto userBirthControlPillsDto1, UserBirthControlPillsDto userBirthControlPillsDto2) {
        assertThat(userBirthControlPillsDto1.id, is(userBirthControlPillsDto2.id));
        assertThat(userBirthControlPillsDto1.pillsTime, is(userBirthControlPillsDto2.pillsTime));
        assertThat(userBirthControlPillsDto1.pillsDate, is(userBirthControlPillsDto2.pillsDate));
        Integer days = userBirthControlPillsDto2.days;
        if(userBirthControlPillsDto1.days == null) {
            assertThat(days, is(21));
        } else {
            assertThat(userBirthControlPillsDto1.days, is(days));
        }
    }
}
