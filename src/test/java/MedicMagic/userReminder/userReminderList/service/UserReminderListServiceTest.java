package MedicMagic.userReminder.userReminderList.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.service.UserService;
import MedicMagic.userReminder.userBirthControlPills.service.UserBirthControlPillsService;
import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;
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
public class UserReminderListServiceTest {
    @Autowired
    UserReminderListService userReminderListService;
    @Autowired
    UserReminderListService testUserReminderListService;
    @Autowired
    UserService userService;
    @Autowired
    UserBirthControlPillsService userBirthControlPillsService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {testUserReminderListService.getAll();}

    @Test
    public void makeTableWhenSignUp() {
        userReminderListService.deleteAll();
        assertThat(userReminderListService.getAll().size(), is(0));

        userBirthControlPillsService.deleteAll();

        userService.deleteAll();
        assertThat(userService.getAll().size(), is(0));

        userService.add(new User("gryffindor", "gryffindor", "gryffindor", "2000-01-01", 22));

        assertThat(userReminderListService.getCountEachId("gryffindor"), is(1));
        assertThat(userReminderListService.get("gryffindor").id, is("gryffindor"));
        assertThat(userReminderListService.get("gryffindor").birthControlPills, is(false));
        assertThat(userReminderListService.get("gryffindor").physiology, is(false));
        assertThat(userReminderListService.get("gryffindor").hospital, is(false));
        assertThat(userReminderListService.get("gryffindor").waterIntake, is(false));
        assertThat(userReminderListService.get("gryffindor").exerciseTimeGoal, is(false));
        assertThat(userReminderListService.get("gryffindor").sleepTimeGoal, is(false));
    }

    static class TestUserReminderListServiceImpl extends UserReminderListServiceImpl {
        @Override
        public List<UserReminderListDto> getAll() {
            for(UserReminderListDto userReminderListDto : super.getAll()) {
                super.update(userReminderListDto);
            }
            return null;
        }

    }
}
