package MedicMagic.userReminder.userReminderPhysiology.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.service.UserService;
import MedicMagic.userReminder.userBirthControlPills.service.UserBirthControlPillsService;
import MedicMagic.userReminder.userReminderList.service.UserReminderListService;
import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;
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
public class UserReminderPhysiologyServiceTest {
    @Autowired
    UserReminderPhysiologyService userReminderPhysiologyService;
    @Autowired
    UserReminderPhysiologyService testUserReminderPhysiologyService;
    @Autowired
    UserBirthControlPillsService userBirthControlPillsService;
    @Autowired
    UserReminderListService userReminderListService;
    @Autowired
    UserService userService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userReminderPhysiologyService.deleteAll();
        assertThat(userReminderPhysiologyService.getAll().size(), is(0));

        userReminderPhysiologyService.add(new UserReminderPhysiologyDto("gryffindor", "null"));
        testUserReminderPhysiologyService.getAll();
    }

    @Test
    public void makeTableWhenSignUp() {
        userReminderPhysiologyService.deleteAll();
        assertThat(userReminderPhysiologyService.getAll().size(), is(0));

        userReminderListService.deleteAll();
        assertThat(userReminderListService.getAll().size(), is(0));

        userBirthControlPillsService.deleteAll();
        assertThat(userBirthControlPillsService.getAll().size(), is(0));

        userService.deleteAll();
        assertThat(userService.getAll().size(), is(0));

        userService.add(new User("gryffindor", "gryffindor", "gryffindor", "2000-01-01", 22));
        assertThat(userReminderPhysiologyService.getCountEachId("gryffindor"), is(1));
        assertThat(userReminderPhysiologyService.get("gryffindor").physiologyTime, is("18:00:00"));
    }

    static class TestUserReminderPhysiology extends UserReminderPhysiologyServiceImpl {
        @Override
        public List<UserReminderPhysiologyDto> getAll() {
            for(UserReminderPhysiologyDto userReminderPhysiologyDto : super.getAll()) {
                super.update(userReminderPhysiologyDto);
            }
            return null;
        }
    }
}
