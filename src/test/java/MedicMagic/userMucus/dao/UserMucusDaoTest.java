package MedicMagic.userMucus.dao;

import MedicMagic.userCalender.dao.DuplicateDateException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.domain.UserMucus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserMucusDaoTest {
    @Autowired
    private UserMucusDao userMucusDao;
    @Autowired
    private DataSource dataSource;

    List<UserCalender> userCalenderList;
    List<UserMucus> userMucusList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        userCalenderList = Arrays.asList(
                new UserCalender("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, null, null, null, null, null, null, true, true),
                new UserCalender("hufflepuff", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, null, null, null, null, null, null, false, false),
                new UserCalender("ravenClaw", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, null, null, null, null, null, null, false, true),
                new UserCalender("slytherin", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, null, null, null, null, null, null, true, false)
        );

        mappingCalenderAndMucus();
    }
    private void mappingCalenderAndMucus() {
        for (UserCalender userCalender : userCalenderList) {
            if(userCalender.isMucus()) {
                userMucusList.add(new UserMucus(userCalender.getId(), userCalender.getDate(), true, false, true, false, true, false, true));
            }
        }
    }

    @Test
    public void connectIdFromUserCalender() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        assertThat(userCalenderList.get(0).getId(), is(userMucusList.get(0).getId()));
        assertThat(userCalenderList.get(0).getDate(), is(userMucusList.get(0).getDate()));
        assertThat(userCalenderList.get(2).getId(), is(userMucusList.get(1).getId()));
        assertThat(userCalenderList.get(2).getDate(), is(userMucusList.get(1).getDate()));
    }

    @Test(expected = DuplicateDateException.class)
    public void duplicateIdAndDate() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusList.get(1).setId(userMucusList.get(0).getId());
        userMucusList.get(1).setDate(userMucusList.get(0).getDate());

        for(UserMucus userMucus : userMucusList) {
            userMucusDao.add(userMucus);
        }
        assertThat(userMucusDao.getCount(), is(1));
    }

    @Test
    public void addAndGet() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        for (UserMucus userMucus : userMucusList) {
            userMucusDao.add(userMucus);
        }
        assertThat(userMucusDao.getCount(), is(2));

        UserMucus userMucusGet1 = userMucusDao.get(userMucusList.get(0).getId(), userMucusList.get(0).getDate());
        checkSameUserMucus(userMucusGet1, userMucusList.get(0));

        UserMucus userMucusGet2 = userMucusDao.get(userMucusList.get(1).getId(), userMucusList.get(1).getDate());
        checkSameUserMucus(userMucusGet2, userMucusList.get(1));
    }

    @Test
    public void getGroupByIdOrDate() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusList.get(1).setId(userMucusList.get(0).getId());
        userMucusList.get(1).setDate("2000-01-01");

        for (UserMucus userMucus : userMucusList) {
            userMucusDao.add(userMucus);
        }
        assertThat(userMucusDao.getCount(), is(2));
        assertThat(userMucusDao.getCountEachId(userMucusList.get(0).getId()), is(2));
        assertThat(userMucusDao.getCountEachIdAndDate(userMucusList.get(0).getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date())), is(1));
        assertThat(userMucusDao.getCountEachIdAndDate(userMucusList.get(0).getId(), "2000-01-01"), is(1));
    }

    @Test
    public void getMucusById() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        for (UserMucus userMucus : userMucusList) {
            userMucusDao.add(userMucus);
        }
        assertThat(userMucusDao.getCount(), is(2));

        List<UserMucus> userMucusListGet = userMucusDao.getEachId(userMucusList.get(0).getId());
        assertThat(userMucusListGet.size(), is(1));
        for(UserMucus userMucus : userMucusListGet) {
            checkSameUserMucus(userMucusList.get(0), userMucus);
        }

        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusList.get(0).setId(userMucusList.get(1).getId());
        userMucusList.get(0).setDate("2000-01-01");
        for (UserMucus userMucus : userMucusList) {
            userMucusDao.add(userMucus);
        }

        List<UserMucus> userMucusListGet2 = userMucusDao.getEachId(userMucusList.get(0).getId());
        assertThat(userMucusListGet2.size(), is(2));
        for (int i = 0; i < userMucusListGet2.size(); i++) {
            checkSameUserMucus(userMucusList.get(i), userMucusListGet2.get(i));
        }
    }

    @Test
    public void getMucusTrue() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDao.add(userMucusList.get(0));
        assertThat(userMucusDao.getCount(), is(1));

        List<String> mucusGet = userMucusDao.getMucusTrue(userMucusDao.get(userMucusList.get(0).getId(), userMucusList.get(0).getDate()));

        assertThat(mucusGet.size(), is(6));
        assertThat(mucusGet.get(0), is(userMucusList.get(0).getId()));
        assertThat(mucusGet.get(1), is(userMucusList.get(1).getDate()));
        assertThat(mucusGet.get(2), is("없음"));
        assertThat(mucusGet.get(3), is("끈적함"));
        assertThat(mucusGet.get(4), is("계란 흰자 같음"));
        assertThat(mucusGet.get(5), is("비정상적임"));
    }

    @Test
    public void update() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        for(UserMucus userMucus : userMucusList) {
            userMucusDao.add(userMucus);
        }
        assertThat(userMucusDao.getCount(), is(2));

        userMucusList.get(1).setWatery(true);
        userMucusDao.update(userMucusDao.get(userMucusList.get(1).getId(), userMucusList.get(1).getDate()), "watery", userMucusList.get(1).isWatery());

        checkSameUserMucus(userMucusList.get(1), userMucusDao.get(userMucusList.get(1).getId(), userMucusList.get(1).getDate()));
    }

    @Test
    public void deleteColumnIfMucusIsAllFalse() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        UserCalender userCalender = new UserCalender(userMucusList.get(0).getId(), userMucusList.get(0).getDate(), null, null, null, null, null, null, null, false, true);
        UserMucus userMucus = new UserMucus(userMucusList.get(0).getId(), userMucusList.get(0).getDate(), false, false, false, false, false, false, true);

        userMucusDao.add(userMucus);
        assertThat(userMucusDao.getCount(), is(1));

        userMucus.setAbnormal(false);
        userMucusDao.update(userMucusDao.get(userMucus.getId(), userMucus.getDate()), "abnormal", userMucus.isAbnormal());
        userMucusDao.updateUserCalenderIfMucusIsFalse(userMucusDao.get(userMucus.getId(), userMucus.getDate()), userCalender);
        assertThat(userMucusDao.getCount(), is(0));
        assertThat(userCalender.isMucus(), is(false));
    }

    @Test
    public void deleteEachIdAndDate() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDao.add(userMucusList.get(0));
        assertThat(userMucusDao.getCount(), is(1));

        userMucusDao.deleteEachIdAndDate(userMucusList.get(0).getId(), userMucusList.get(0).getDate());
        assertThat(userMucusDao.getCount(), is(0));
    }

    private void checkSameUserMucus(UserMucus userMucus1, UserMucus userMucus2) {
        assertThat(userMucus1.getId(), is(userMucus2.getId()));
        assertThat(userMucus1.getDate(), is(userMucus2.getDate()));
        assertThat(userMucus1.isNone(), is(userMucus2.isNone()));
        assertThat(userMucus1.isMottled(), is(userMucus2.isMottled()));
        assertThat(userMucus1.isCreamy(), is(userMucus2.isCreamy()));
        assertThat(userMucus1.isSticky(), is(userMucus2.isSticky()));
        assertThat(userMucus1.isLikeEggWhite(), is(userMucus2.isLikeEggWhite()));
        assertThat(userMucus1.isWatery(), is(userMucus2.isWatery()));
        assertThat(userMucus1.isAbnormal(), is(userMucus2.isAbnormal()));
    }
}
