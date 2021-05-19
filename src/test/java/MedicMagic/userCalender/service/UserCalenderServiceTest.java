package MedicMagic.userCalender.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userPhysiology.dao.UserPhysiologyDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserCalenderServiceTest {
    @Autowired
    UserCalenderService testUserCalenderService;
    @Autowired
    UserCalenderService userCalenderService;
    @Autowired
    UserPhysiologyDao userPhysiologyDao;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        testUserCalenderService.getAll();
    }

    @Test
    public void createUserPhysiologyDao() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));
        userCalenderService.deleteAll();

        UserCalender userCalender = new UserCalender("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 51.4, "07:30:00", "01:30:00", 1.5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, "Αρ°ΕΏς", true, true);
        userCalenderService.add(userCalender);
        assertThat(userPhysiologyDao.getCount(), is(1));
    }

    static class TestUserCalenderServiceImpl extends UserCalenderServiceImpl {
        @Override
        public List<UserCalender> getAll() {
            for(UserCalender userCalender : super.getAll()) {
                super.update(userCalender);
            }
            return null;
        }
    }
}
