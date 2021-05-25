package MedicMagic.userReminder.userSleep.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.service.UserService;
import MedicMagic.userReminder.userBirthControlPills.service.UserBirthControlPillsService;
import MedicMagic.userReminder.userExercise.service.UserExerciseService;
import MedicMagic.userReminder.userHospital.service.UserHospitalService;
import MedicMagic.userReminder.userReminderList.service.UserReminderListService;
import MedicMagic.userReminder.userReminderPhysiology.service.UserReminderPhysiologyService;
import MedicMagic.userReminder.userSleep.dto.UserSleepDto;
import MedicMagic.userReminder.userWaterIntake.service.UserWaterIntakeService;
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
public class UserSleepServiceTest {
    @Autowired
    UserSleepService userSleepService;
    @Autowired
    UserSleepService testUserSleepService;
    @Autowired
    UserReminderListService userReminderListService;
    @Autowired
    UserBirthControlPillsService userBirthControlPillsService;
    @Autowired
    UserReminderPhysiologyService userReminderPhysiologyService;
    @Autowired
    UserHospitalService userHospitalService;
    @Autowired
    UserExerciseService userExerciseService;
    @Autowired
    UserWaterIntakeService userWaterIntakeService;
    @Autowired
    UserService userService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userSleepService.deleteAll();
        assertThat(userSleepService.getAll().size(), is(0));

        userSleepService.add(new UserSleepDto("gryffindor", "0.0"));
        assertThat(userSleepService.getAll().size(), is(1));

        testUserSleepService.getAll();
    }

    @Test
    public void makeTableWhenSighUp() {
        userSleepService.deleteAll();
        assertThat(userSleepService.getAll().size(), is(0));

        userReminderListService.deleteAll();
        assertThat(userReminderListService.getAll().size(), is(0));

        userBirthControlPillsService.deleteAll();
        assertThat(userBirthControlPillsService.getAll().size(), is(0));

        userReminderPhysiologyService.deleteAll();
        assertThat(userReminderPhysiologyService.getAll().size(), is(0));

        userHospitalService.deleteAll();
        assertThat(userHospitalService.getAll().size(), is(0));

        userExerciseService.deleteAll();
        assertThat(userExerciseService.getAll().size(), is(0));

        userWaterIntakeService.deleteAll();
        assertThat(userWaterIntakeService.getAll().size(), is(0));

        userService.deleteAll();
        assertThat(userService.getAll().size(), is(0));

        userService.add(new User("gryffindor", "gryffindor", "gryffindor", "2000-01-01", 22));
        assertThat(userService.getAll().size(), is(1));

        assertThat(userSleepService.getCountEachId("gryffindor"), is(1));
        assertThat(userSleepService.get("gryffindor").sleepGoal, is(0.0));
    }

    static class TestUserSleepService extends UserSleepServiceImpl{
        @Override
        public List<UserSleepDto> getAll() {
            for(UserSleepDto userSleepDto : super.getAll()) {
                super.update(userSleepDto);
            }
            return null;
        }
    }
}
