package MedicMagic.userReminder.userReminderList.dao;

import MedicMagic.userReminder.userReminderList.domain.UserReminderList;
import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserReminderListDaoTest {
    @Autowired
    private UserReminderListDao userReminderListDao;
    @Autowired
    private DataSource dataSource;

    private UserReminderList userReminderList1;
    private UserReminderList userReminderList2;
    private UserReminderList userReminderList3;

    private UserReminderListDto userReminderListDto1;
    private UserReminderListDto userReminderListDto2;
    private UserReminderListDto userReminderListDto3;

    @Before
    public void setUp() throws Exception {
        userReminderList1 = new UserReminderList("gryffindor", true, true, true, true, true, true);
        userReminderList2 = new UserReminderList("hufflepuff", false, false, false, false, false, false);
        userReminderList3 = new UserReminderList("slyderin", true, false, true, false, true, false);

        userReminderListDto1 = new UserReminderListDto(userReminderList1);
        userReminderListDto2 = new UserReminderListDto(userReminderList2);
        userReminderListDto3 = new UserReminderListDto(userReminderList3);
    }

    @Test
    public void addAndGet() {
        userReminderListDao.deleteAll();
        assertThat(userReminderListDao.getCount(), is(0));

        userReminderListDao.add(userReminderListDto1);
        assertThat(userReminderListDao.getCount(), is(1));

        userReminderListDao.add(userReminderListDto2);
        assertThat(userReminderListDao.getCount(), is(2));

        userReminderListDao.add(userReminderListDto3);
        assertThat(userReminderListDao.getCount(), is(3));

        checkSameUserReminderList(userReminderListDto1, userReminderListDao.get(userReminderListDto1.id));
        checkSameUserReminderList(userReminderListDto2, userReminderListDao.get(userReminderListDto2.id));
        checkSameUserReminderList(userReminderListDto3, userReminderListDao.get(userReminderListDto3.id));

        List<UserReminderListDto> userReminderListDtoList = userReminderListDao.getAll();
        checkSameUserReminderList(userReminderListDto1, userReminderListDtoList.get(0));
        checkSameUserReminderList(userReminderListDto2, userReminderListDtoList.get(1));
        checkSameUserReminderList(userReminderListDto3, userReminderListDtoList.get(2));
    }

    private void checkSameUserReminderList(UserReminderListDto userReminderListDto1, UserReminderListDto userReminderListDto2) {
        assertThat(userReminderListDto1.id, is(userReminderListDto2.id));
        assertThat(userReminderListDto1.birthControlPills, is(userReminderListDto2.birthControlPills));
        assertThat(userReminderListDto1.physiology, is(userReminderListDto2.physiology));
        assertThat(userReminderListDto1.hospital, is(userReminderListDto2.hospital));
        assertThat(userReminderListDto1.waterIntake, is(userReminderListDto2.waterIntake));
        assertThat(userReminderListDto1.exerciseTimeGoal, is(userReminderListDto2.exerciseTimeGoal));
        assertThat(userReminderListDto1.sleepTimeGoal, is(userReminderListDto2.sleepTimeGoal));
    }
}
