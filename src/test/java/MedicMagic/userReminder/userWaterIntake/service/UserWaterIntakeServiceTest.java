package MedicMagic.userReminder.userWaterIntake.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.service.UserService;
import MedicMagic.userReminder.userBirthControlPills.service.UserBirthControlPillsService;
import MedicMagic.userReminder.userHospital.service.UserHospitalService;
import MedicMagic.userReminder.userReminderList.service.UserReminderListService;
import MedicMagic.userReminder.userReminderPhysiology.service.UserReminderPhysiologyService;
import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserWaterIntakeServiceTest {
    @Autowired
    UserWaterIntakeService userWaterIntakeService;
    @Autowired
    UserWaterIntakeService testUserWaterIntakeService;
    @Autowired
    UserService userService;
    @Autowired
    UserReminderListService userReminderListService;
    @Autowired
    UserBirthControlPillsService userBirthControlPillsService;
    @Autowired
    UserReminderPhysiologyService userReminderPhysiologyService;
    @Autowired
    UserHospitalService userHospitalService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userWaterIntakeService.deleteAll();
        assertThat(userWaterIntakeService.getAll().size(), is(0));

        userWaterIntakeService.add(new UserWaterIntakeDto("gryffindor", "2.0", "null"));
        assertThat(userWaterIntakeService.getAll().size(), is(1));

        testUserWaterIntakeService.getAll();
    }

    @Test
    public void makeTableWhenSignUp() {
        userWaterIntakeService.deleteAll();
        assertThat(userWaterIntakeService.getAll().size(), is(0));

        userReminderListService.deleteAll();
        assertThat(userReminderListService.getAll().size(), is(0));

        userBirthControlPillsService.deleteAll();
        assertThat(userBirthControlPillsService.getAll().size(), is(0));

        userReminderPhysiologyService.deleteAll();
        assertThat(userReminderPhysiologyService.getAll().size(), is(0));

        userHospitalService.deleteAll();
        assertThat(userHospitalService.getAll().size(), is(0));

        userService.deleteAll();
        assertThat(userService.getAll().size(), is(0));

        userService.add(new User("gryffindor", "gryffindor", "gryffindor", "2001-01-01", 21));
        assertThat(userService.getAll().size(), is(1));

        assertThat(userWaterIntakeService.getCountEachId("gryffindor"), is(1));
        assertThat(userWaterIntakeService.get("gryffindor").cups, is(0.0));
        assertThat(userWaterIntakeService.get("gryffindor").waterTime, is("22:00:00"));
    }

    static class TestUserWaterIntakeService extends UserWaterIntakeServiceImpl {
        @Override
        public List<UserWaterIntakeDto> getAll() {
            for(UserWaterIntakeDto userWaterIntakeDto : super.getAll()) {
                super.update(userWaterIntakeDto);
            }
            return null;
        }
    }
}
