package MedicMagic.userReminder.userBirthControlPills.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.service.UserService;
import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;
import MedicMagic.userReminder.userReminderList.service.UserReminderListService;
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
public class UserBirthControlPillsServiceTest {
    @Autowired
    UserBirthControlPillsService testUserBirthControlPillsService;
    @Autowired
    UserBirthControlPillsService userBirthControlPillsService;
    @Autowired
    UserService userService;
    @Autowired
    UserReminderListService userReminderListService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userBirthControlPillsService.deleteAll();
        assertThat(userBirthControlPillsService.getAll().size(), is(0));

        userBirthControlPillsService.add(new UserBirthControlPillsDto("gryffindor", "08:00:00", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "21"));
        testUserBirthControlPillsService.getAll();
    }

    @Test
    public void makeTableWhenSignUp() {
        userBirthControlPillsService.deleteAll();
        assertThat(userBirthControlPillsService.getAll().size(), is(0));

        userReminderListService.deleteAll();

        userService.deleteAll();
        assertThat(userService.getAll().size(), is(0));

        userService.add(new User("gryffindor", "gryffindor", "gryffindor", "2000-01-01", 22));

        assertThat(userBirthControlPillsService.getCountEachId("gryffindor"), is(1));
        assertThat(userBirthControlPillsService.get("gryffindor").pillsTime, is("08:00:00"));
        assertThat(userBirthControlPillsService.get("gryffindor").pillsDate, is(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
        assertThat(userBirthControlPillsService.get("gryffindor").days, is(21));
    }

    static class TestUserBirthControlPillsServiceImpl extends UserBirthControlPillsServiceImpl {
        @Override
        public List<UserBirthControlPillsDto> getAll() {
            for(UserBirthControlPillsDto userBirthControlPillsDto : super.getAll()) {
                super.update(userBirthControlPillsDto);
            }
            return null;
        }
    }
}
