package MedicMagic.userReminder.userHospital.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.service.UserService;
import MedicMagic.userPhysiology.service.UserPhysiologyService;
import MedicMagic.userReminder.userBirthControlPills.service.UserBirthControlPillsService;
import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;
import MedicMagic.userReminder.userReminderList.service.UserReminderListService;
import MedicMagic.userReminder.userReminderPhysiology.service.UserReminderPhysiologyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserHospitalServiceTest {
    @Autowired
    UserHospitalService userHospitalService;
    @Autowired
    UserHospitalService testUserHospitalService;
    @Autowired
    UserReminderListService userReminderListService;
    @Autowired
    UserBirthControlPillsService userBirthControlPillsService;
    @Autowired
    UserReminderPhysiologyService userReminderPhysiologyService;
    @Autowired
    UserService userService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userHospitalService.deleteAll();
        assertThat(userHospitalService.getAll().size(), is(0));

        userHospitalService.add(new UserHospitalDto("gryffindor", "null", "null"));
        assertThat(userHospitalService.getAll().size(), is(1));

        testUserHospitalService.getAll();
    }

    @Test
    public void makeTableWhenSignUp() {
        userHospitalService.deleteAll();
        assertThat(userHospitalService.getAll().size(), is(0));

        userReminderListService.deleteAll();
        assertThat(userReminderListService.getAll().size(), is(0));

        userBirthControlPillsService.deleteAll();
        assertThat(userBirthControlPillsService.getAll().size(), is(0));

        userReminderPhysiologyService.deleteAll();
        assertThat(userReminderPhysiologyService.getAll().size(), is(0));

        userService.deleteAll();
        assertThat(userService.getAll().size(), is(0));

        userService.add(new User("gryffindor", "gryffindor", "gryffindor", "2000-01-01", 22));
        assertThat(userService.getAll().size(), is(1));
        assertThat(userHospitalService.getCountEachId("gryffindor"), is(1));
        assertThat(userHospitalService.get("gryffindor").hospitalDate, is(nullValue()));
        assertThat(userHospitalService.get("gryffindor").hospitalTime, is("18:00:00"));
    }

    static class TestUserHospitalServiceImpl extends UserHospitalServiceImpl {
        @Override
        public List<UserHospitalDto> getAll() {
            for(UserHospitalDto userHospitalDto : super.getAll()) {
                super.update(userHospitalDto);
            }
            return null;
        }
    }
}
