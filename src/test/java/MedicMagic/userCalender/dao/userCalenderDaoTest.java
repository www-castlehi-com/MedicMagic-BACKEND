package MedicMagic.userCalender.dao;

import MedicMagic.user.domain.User;
import MedicMagic.exception.DuplicateDateException;
import MedicMagic.exception.NegativeException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class userCalenderDaoTest {
    @Autowired
    private UserCalenderDao userCalenderDao;
    @Autowired
    private DataSource dataSource;

    private UserCalender userCalender1;
    private UserCalender userCalender2;

    private UserCalenderDto userCalenderDto1;
    private UserCalenderDto userCalenderDto2;
    private UserCalenderDto userCalenderDto3;

    @Before
    public void setUp() throws Exception {
        userCalender1 = new UserCalender("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "08:30:00", null, 3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);
        userCalender2 = new UserCalender("hufflepuff", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, "02:30:00", 0, null, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        userCalenderDto1 = new UserCalenderDto(userCalender1);
        userCalenderDto2 = new UserCalenderDto(userCalender2);

        userCalenderDto3 = new UserCalenderDto("slyderin", "2021-05-26", "null", "02:30:00", "2", "null", "2021-05-26");
    }

    @Test
    public void addAndGet() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalenderDto1);
        assertThat(userCalenderDao.getCount(), is(1));

        userCalenderDao.add(userCalenderDto2);
        assertThat(userCalenderDao.getCount(), is(2));

        checkSameUserCalender(userCalenderDao.get(userCalender1.getId(), userCalender1.getDate()), userCalenderDto1);
        checkSameUserCalender(userCalenderDao.get(userCalender2.getId(), userCalender2.getDate()), userCalenderDto2);
    }

    @Test
    public void getGroupById() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDto2.id = userCalender1.getId();
        userCalenderDto2.date = "2000-01-01";

        userCalenderDao.add(userCalenderDto1);
        userCalenderDao.add(userCalenderDto2);
        assertThat(userCalenderDao.getCount(), is(2));
        assertThat(userCalenderDao.getCountEachId(userCalender1.getId()), is(2));

        List<UserCalenderDto> userCalenders = userCalenderDao.getEachId(userCalender1.getId());
        assertThat(userCalenders.size(), is(2));
        checkSameUserCalender(userCalenders.get(0), userCalenderDto2);
        checkSameUserCalender(userCalenders.get(1), userCalenderDto1);
    }

    @Test
    public void update() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalenderDto1);
        userCalenderDao.add(userCalenderDto2);

        userCalenderDto2.waterIntake = 2;
        userCalenderDao.update(userCalenderDto2);

        UserCalenderDto userCalenderGet1 = userCalenderDao.get(userCalender1.getId(), userCalender1.getDate());
        checkSameUserCalender(userCalenderGet1, userCalenderDto1);
        UserCalenderDto userCalenderGet2 = userCalenderDao.get(userCalender2.getId(), userCalender2.getDate());
        checkSameUserCalender(userCalenderGet2, userCalenderDto2);
    }

    @Test
    public void deleteEachIdAndDate() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalenderDto1);
        assertThat(userCalenderDao.getCount(), is(1));

        userCalenderDao.deleteEachIdAndDate(userCalender1.getId(), userCalender1.getDate());
        assertThat(userCalenderDao.getCount(), is(0));
    }

    private void checkSameUserCalender(UserCalenderDto userCalenderDto1, UserCalenderDto userCalenderDto2) {
        assertThat(userCalenderDto1.id, is(userCalenderDto2.id));
        assertThat(userCalenderDto1.date, is(userCalenderDto2.date));
        assertThat(userCalenderDto1.sleepTime, is(userCalenderDto2.sleepTime));
        assertThat(userCalenderDto1.exerciseTime, is(userCalenderDto2.exerciseTime));
        assertThat(userCalenderDto1.waterIntake, is(userCalenderDto2.waterIntake));
        assertThat(userCalenderDto1.startDay, is(userCalenderDto2.startDay));
        assertThat(userCalenderDto1.endDay, is(userCalenderDto2.endDay));
        assertThat(userCalenderDto1.waterIntake, is(userCalenderDto2.waterIntake));
    }
}
