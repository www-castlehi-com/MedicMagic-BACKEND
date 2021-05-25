package MedicMagic.userReminder.userExercise.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.service.UserService;
import MedicMagic.userReminder.userBirthControlPills.service.UserBirthControlPillsService;
import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;
import MedicMagic.userReminder.userHospital.service.UserHospitalService;
import MedicMagic.userReminder.userReminderList.service.UserReminderListService;
import MedicMagic.userReminder.userReminderPhysiology.service.UserReminderPhysiologyService;
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
public class UserExerciseServiceTest {
    @Autowired
    UserExerciseService userExerciseService;
    @Autowired
    UserExerciseService testUserExerciseService;
    @Autowired
    UserReminderListService userReminderListService;
    @Autowired
    UserBirthControlPillsService userBirthControlPillsService;
    @Autowired
    UserReminderPhysiologyService userReminderPhysiologyService;
    @Autowired
    UserHospitalService userHospitalService;
    @Autowired
    UserWaterIntakeService userWaterIntakeService;
    @Autowired
    UserService userService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userExerciseService.deleteAll();
        assertThat(userExerciseService.getAll().size(), is(0));

        userExerciseService.add(new UserExerciseDto("gryffindor", "3.5"));
        assertThat(userExerciseService.getAll().size(), is(1));

        testUserExerciseService.getAll();
    }

    @Test
    public void makeTableWhenSighUp() {
        userExerciseService.deleteAll();
        assertThat(userExerciseService.getAll().size(), is(0));

        userReminderListService.deleteAll();
        assertThat(userReminderListService.getAll().size(), is(0));

        userBirthControlPillsService.deleteAll();
        assertThat(userBirthControlPillsService.getAll().size(), is(0));

        userReminderPhysiologyService.deleteAll();
        assertThat(userReminderPhysiologyService.getAll().size(), is(0));

        userHospitalService.deleteAll();
        assertThat(userHospitalService.getAll().size(), is(0));

        userWaterIntakeService.deleteAll();
        assertThat(userWaterIntakeService.getAll().size(), is(0));

        userService.deleteAll();
        assertThat(userService.getAll().size(), is(0));

        userService.add(new User("gryffindor", "gryffindor", "gryffindor", "2000-01-01", 22));
        assertThat(userService.getAll().size(), is(1));

        assertThat(userExerciseService.getCountEachId("gryffindor"), is(1));
        assertThat(userExerciseService.get("gryffindor").exerciseHour, is(0.0));
    }

    static class TestUserExerciseService extends UserExerciseServiceImpl {
        @Override
        public List<UserExerciseDto> getAll() {
            for(UserExerciseDto userExerciseDto : super.getAll()) {
                super.update(userExerciseDto);
            }
            return null;
        }
    }
}
