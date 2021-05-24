package MedicMagic.userReminder.userReminderPhysiology.dao;

import MedicMagic.userReminder.userReminderPhysiology.domain.UserReminderPhysiology;
import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;
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
public class UserReminderPhysiologyDaoTest {
    @Autowired
    UserReminderPhysiologyDao userReminderPhysiologyDao;
    @Autowired
    DataSource dataSource;

    UserReminderPhysiology userReminderPhysiology1;
    UserReminderPhysiology userReminderPhysiology2;

    UserReminderPhysiologyDto userReminderPhysiologyDto1;
    UserReminderPhysiologyDto userReminderPhysiologyDto2;

    UserReminderPhysiologyDto userReminderPhysiologyDto3;

    @Before
    public void setUp() throws Exception {
        userReminderPhysiology1 = new UserReminderPhysiology("gryffindor", "09:00:00");
        userReminderPhysiology2 = new UserReminderPhysiology("hufflepuff", null);

        userReminderPhysiologyDto1 = new UserReminderPhysiologyDto(userReminderPhysiology1);
        userReminderPhysiologyDto2 = new UserReminderPhysiologyDto(userReminderPhysiology2);

        userReminderPhysiologyDto3 = new UserReminderPhysiologyDto("ravenclaw", "null");
    }

    @Test
    public void addAndGet() {
        userReminderPhysiologyDao.deleteAll();
        assertThat(userReminderPhysiologyDao.getCount(), is(0));

        userReminderPhysiologyDao.add(userReminderPhysiologyDto1);
        assertThat(userReminderPhysiologyDao.getCount(), is(1));

        userReminderPhysiologyDao.add(userReminderPhysiologyDto2);
        assertThat(userReminderPhysiologyDao.getCount(), is(2));

        checkSameUserReminderPhysiology(userReminderPhysiologyDao.get(userReminderPhysiologyDto1.id), userReminderPhysiologyDto1);
        checkSameUserReminderPhysiology(userReminderPhysiologyDao.get(userReminderPhysiologyDto2.id), userReminderPhysiologyDto2);
    }

    @Test
    public void stringTypeAddAndGet() {
        userReminderPhysiologyDao.deleteAll();
        assertThat(userReminderPhysiologyDao.getCount(), is(0));

        userReminderPhysiologyDao.add(userReminderPhysiologyDto3);
        assertThat(userReminderPhysiologyDao.getCount(), is(1));

        assertThat(userReminderPhysiologyDao.get(userReminderPhysiologyDto3.id).id, is(userReminderPhysiologyDto3.id));
        assertThat(userReminderPhysiologyDao.get(userReminderPhysiologyDto3.id).physiologyTime, is("18:00:00"));
    }

    private void checkSameUserReminderPhysiology(UserReminderPhysiologyDto userReminderPhysiologyDto1, UserReminderPhysiologyDto userReminderPhysiologyDto2) {
        assertThat(userReminderPhysiologyDto1.id, is(userReminderPhysiologyDto2.id));
        assertThat(userReminderPhysiologyDto1.physiologyTime, is(userReminderPhysiologyDto2.physiologyTime));
    }
}
