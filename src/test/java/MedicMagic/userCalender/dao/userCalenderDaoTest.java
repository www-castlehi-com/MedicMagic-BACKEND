package MedicMagic.userCalender.dao;

import MedicMagic.user.domain.User;
import MedicMagic.userCalender.DuplicateDateException;
import MedicMagic.userCalender.NegativeException;
import MedicMagic.userCalender.domain.UserCalender;
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

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    private UserCalender userCalender1;
    private UserCalender userCalender2;
    private UserCalender userCalender3;
    private UserCalender userCalender4;

    @Before
    public void setUp() throws Exception {
        this.user1 = new User("gryffindor", "그린핀도르", "19011655", "2000-05-21", 22);
        this.user2 = new User("hufflepuff", "후플푸프", "19011637", "1999-05-29", 22);
        this.user3 = new User("ravenclaw", "레번클로", "19011671", "2000-06-17", 22);
        this.user4 = new User("slytherin", "슬리데린", "19011668", "2000-04-09", 22);


        userCalender1 = new UserCalender(null, null, 51.4, "07:30:00", "01:30:00", 1.5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, "즐거움", true, true);
        userCalender2 = new UserCalender(null, null, -49.5, "01:20:00", "00:00:00", 0.0, null, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "변덕스러움", false, false);
        userCalender3 = new UserCalender(null, null, null, null, null, null, null, null, null, false, false);
        userCalender4 = new UserCalender(null, null, 52.5, "13:00:00", "02:00:00", 2.5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, true, true);

        userCalender1.setId(user1.getId());
        userCalender2.setId(user2.getId());
        userCalender3.setId(user3.getId());
        userCalender4.setId(user4.getId());
        userCalender1.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        userCalender2.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        userCalender3.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        userCalender4.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    @Test
    public void connectIdFromUserAndDate() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        assertThat(userCalender1.getId(), is(user1.getId()));
        assertThat(userCalender2.getId(), is(user2.getId()));
        assertThat(userCalender3.getId(), is(user3.getId()));
        assertThat(userCalender4.getId(), is(user4.getId()));
    }

    @Test(expected = DuplicateDateException.class)
    public void duplicateIdAndDate() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalender4.setId(userCalender1.getId());
        userCalender4.setDate(userCalender1.getDate());

        userCalenderDao.add(userCalender4);
        userCalenderDao.add(userCalender4);
        assertThat(userCalenderDao.getCount(), is(1));
    }

    @Test(expected = NegativeException.class)
    public void negativeDouble() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalender2);
        assertThat(userCalenderDao.getCount(), is(0));
    }

    @Test
    public void canUpdateNull() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalender3);
        assertThat(userCalenderDao.getCount(), is(1));

        UserCalender checkNullUser = userCalenderDao.get(userCalender3.getId(), userCalender3.getDate());
        assertThat(checkNullUser.getWeigh(), is(0.0)); // null value
        assertThat(checkNullUser.getSleepTime(), is(nullValue()));
        assertThat(checkNullUser.getExerciseTime(), is(nullValue()));
        assertThat(checkNullUser.getWaterIntake(), is(0.0)); //null value
        assertThat(checkNullUser.getStartDay(), is(nullValue()));
        assertThat(checkNullUser.getEndDay(), is(nullValue()));
        assertThat(checkNullUser.getEmotion(), is(nullValue()));
        assertThat(checkNullUser.isSymptom(), is(false));
    }

    @Test
    public void addAndGet() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalender1);
        userCalenderDao.add(userCalender4);
        assertThat(userCalenderDao.getCount(), is(2));

        UserCalender userCalenderGet1 = userCalenderDao.get(userCalender1.getId(), userCalender1.getDate());
        checkSameUserCalender(userCalenderGet1, userCalender1);

        UserCalender userCalenderGet2 = userCalenderDao.get(userCalender4.getId(), userCalender4.getDate());
        checkSameUserCalender(userCalenderGet2, userCalender4);
    }

    @Test
    public void getGroupById() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalender4.setId(userCalender1.getId());
        userCalender4.setDate("2000-01-01");

        userCalenderDao.add(userCalender1);
        userCalenderDao.add(userCalender4);
        assertThat(userCalenderDao.getCount(), is(2));
        assertThat(userCalenderDao.getCountEachId(userCalender1.getId()), is(2));

        List<UserCalender> userCalenders = userCalenderDao.getEachId(userCalender1.getId());
        assertThat(userCalenders.size(), is(2));
        checkSameUserCalender(userCalenders.get(0), userCalender4);
        checkSameUserCalender(userCalenders.get(1), userCalender1);
    }

    @Test
    public void update() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalender1);
        userCalenderDao.add(userCalender4);

        userCalender4.setWeigh(100.2);
        userCalenderDao.update(userCalender4);

        UserCalender userCalenderGet1 = userCalenderDao.get(userCalender1.getId(), userCalender1.getDate());
        checkSameUserCalender(userCalenderGet1, userCalender1);
        UserCalender userCalenderGet2 = userCalenderDao.get(userCalender4.getId(), userCalender4.getDate());
        checkSameUserCalender(userCalenderGet2, userCalender4);
    }

    @Test
    public void deleteEachIdAndDate() {
        userCalenderDao.deleteAll();
        assertThat(userCalenderDao.getCount(), is(0));

        userCalenderDao.add(userCalender1);
        assertThat(userCalenderDao.getCount(), is(1));

        userCalenderDao.deleteEachIdAndDate(userCalender1.getId(), userCalender1.getDate());
        assertThat(userCalenderDao.getCount(), is(0));
    }

    private void checkSameUserCalender(UserCalender userCalender1, UserCalender userCalender2) {
        assertThat(userCalender1.getId(), is(userCalender2.getId()));
        assertThat(userCalender1.getDate(), is(userCalender2.getDate()));
        assertThat(userCalender1.getWeigh(), is(userCalender2.getWeigh()));
        assertThat(userCalender1.getSleepTime(), is(userCalender2.getSleepTime()));
        assertThat(userCalender1.getExerciseTime(), is(userCalender2.getExerciseTime()));
        assertThat(userCalender1.getWaterIntake(), is(userCalender2.getWaterIntake()));
        assertThat(userCalender1.getStartDay(), is(userCalender2.getStartDay()));
        assertThat(userCalender1.getEndDay(), is(userCalender2.getEndDay()));
        assertThat(userCalender1.getEmotion(), is(userCalender2.getEmotion()));
        assertThat(userCalender1.isSymptom(), is(userCalender2.isSymptom()));
        assertThat(userCalender1.isMucus(), is(userCalender2.isMucus()));
    }
}
