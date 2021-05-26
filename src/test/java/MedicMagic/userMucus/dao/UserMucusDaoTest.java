package MedicMagic.userMucus.dao;

import MedicMagic.exception.DuplicateDateException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userMucus.dto.UserMucusDto;
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

    private UserMucus userMucus1;
    private UserMucus userMucus2;

    private UserMucusDto userMucusDto1;
    private UserMucusDto userMucusDto2;

    private UserMucusDto userMucusDto3;

    @Before
    public void setUp() throws Exception {
        userMucus1 = new UserMucus("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), false, false, false, false, false, false, false);
        userMucus2 = new UserMucus("hufflepuff", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), false, false, true, true ,false, false, true);

        userMucusDto1 = new UserMucusDto(userMucus1);
        userMucusDto2 = new UserMucusDto(userMucus2);

        userMucusDto3 = new UserMucusDto("hufflepuff", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "false", "false", "true", "true", "false", "false", "true");
    }

    @Test
    public void addAndGet() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDao.add(userMucusDto1);
        assertThat(userMucusDao.getCount(), is(1));

        userMucusDao.add(userMucusDto2);
        assertThat(userMucusDao.getCount(), is(2));

        checkSameUserMucus(userMucusDao.get(userMucusDto1.id, userMucusDto1.date), userMucusDto1);
        checkSameUserMucus(userMucusDao.get(userMucusDto2.id, userMucusDto2.date), userMucusDto2);
    }

    @Test
    public void getGroupByIdOrDate() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDto2.id = userMucusDto1.id;
        userMucusDto2.date = "2000-01-01";

        userMucusDao.add(userMucusDto1);
        userMucusDao.add(userMucusDto2);
        assertThat(userMucusDao.getCount(), is(2));

        assertThat(userMucusDao.getCountEachId(userMucusDto1.id), is(2));
        assertThat(userMucusDao.getCountEachIdAndDate(userMucusDto1.id, new SimpleDateFormat("yyyy-MM-dd").format(new Date())), is(1));
        assertThat(userMucusDao.getCountEachIdAndDate(userMucusDto1.id, "2000-01-01"), is(1));
    }

    @Test
    public void getMucusById() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDao.add(userMucusDto1);
        userMucusDao.add(userMucusDto2);
        assertThat(userMucusDao.getCount(), is(2));

        List<UserMucusDto> userMucusListGet = userMucusDao.getEachId(userMucusDto1.id);
        assertThat(userMucusListGet.size(), is(1));
        for(UserMucusDto userMucusDto : userMucusListGet) {
            checkSameUserMucus(userMucusDto1, userMucusDto);
        }

        userMucusDto2.id = userMucusDto1.id;
        userMucusDto2.date = "2000-01-01";
        userMucusDao.add(userMucusDto2);

        List<UserMucusDto> userMucusListGet2 = userMucusDao.getEachId(userMucusDto1.id);
        assertThat(userMucusListGet2.size(), is(2));
        checkSameUserMucus(userMucusListGet2.get(0), userMucusDto2);
        checkSameUserMucus(userMucusListGet2.get(1), userMucusDto1);
    }

    @Test
    public void update() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDao.add(userMucusDto1);
        userMucusDao.add(userMucusDto2);
        assertThat(userMucusDao.getCount(), is(2));

        userMucusDto1.watery = true;

        userMucusDao.update(userMucusDto1);

        checkSameUserMucus(userMucusDto1, userMucusDao.get(userMucusDto1.id, userMucusDto1.date));
    }

    @Test
    public void deleteEachIdAndDate() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDao.add(userMucusDto1);
        assertThat(userMucusDao.getCount(), is(1));

        userMucusDao.deleteEachIdAndDate(userMucusDto1.id, userMucusDto1.date);
        assertThat(userMucusDao.getCount(), is(0));
    }

    @Test
    public void stringTypeAddAndGet() {
        userMucusDao.deleteAll();
        assertThat(userMucusDao.getCount(), is(0));

        userMucusDao.add(userMucusDto3);
        assertThat(userMucusDao.getCount(), is(1));
        checkSameUserMucus(userMucusDto2, userMucusDao.get(userMucusDto3.id, userMucusDto3.date));
    }

    private void checkSameUserMucus(UserMucusDto userMucusDto1, UserMucusDto userMucusDto2) {
        assertThat(userMucusDto1.id, is(userMucusDto2.id));
        assertThat(userMucusDto1.date, is(userMucusDto2.date));
        assertThat(userMucusDto1.none, is(userMucusDto2.none));
        assertThat(userMucusDto1.mottled, is(userMucusDto2.mottled));
        assertThat(userMucusDto1.sticky, is(userMucusDto2.sticky));
        assertThat(userMucusDto1.creamy, is(userMucusDto2.creamy));
        assertThat(userMucusDto1.likeEggWhite, is(userMucusDto2.likeEggWhite));
        assertThat(userMucusDto1.watery, is(userMucusDto2.watery));
        assertThat(userMucusDto1.abnormal, is(userMucusDto2.abnormal));
    }
}
