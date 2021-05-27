package MedicMagic.userCalender.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userMucus.service.UserMucusService;
import MedicMagic.userPhysiology.dao.UserPhysiologyDao;
import MedicMagic.userPhysiology.service.UserPhysiologyService;
import MedicMagic.userPhysiology.service.UserPhysiologyServiceTest;
import MedicMagic.userSymptom.service.UserSymptomService;
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
    UserPhysiologyService userPhysiologyService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userPhysiologyService.deleteAll();
        assertThat(userPhysiologyService.getAll().size(), is(0));

        userCalenderService.deleteAll();
        assertThat(userCalenderService.getAll().size(), is(0));

        userCalenderService.add(new UserCalenderDto(new UserCalender("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "08:30:00", null, 3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null)));
        assertThat(userCalenderService.getAll().size(), is(1));

        testUserCalenderService.getAll();
    }

    @Test
    public void createRelatedDao() {
        userPhysiologyService.deleteAll();
        assertThat(userPhysiologyService.getAll().size(), is(0));

        userCalenderService.deleteAll();
        assertThat(userCalenderService.getAll().size(), is(0));

        UserCalender userCalender = new UserCalender("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "07:30:00", "01:30:00", 1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);
        UserCalenderDto userCalenderDto = new UserCalenderDto(userCalender);
        userCalenderService.add(userCalenderDto);
        assertThat(userCalenderService.getCountEachIdAndDate("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date())), is(1));

        assertThat(userPhysiologyService.getCountEachId("gryffindor"), is(1));
        assertThat(userCalenderService.getCountEachIdAndDate("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date())), is(1));
    }

    static class TestUserCalenderServiceImpl extends UserCalenderServiceImpl {
        @Override
        public List<UserCalenderDto> getAll() {
            for(UserCalenderDto userCalenderDto : super.getAll()) {
                super.update(userCalenderDto);
            }
            return null;
        }
    }
}
