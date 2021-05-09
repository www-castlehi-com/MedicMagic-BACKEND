package MedicMagic.userSymptom.dao;

import MedicMagic.userCalender.dao.DuplicateDateException;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserSymptomDaoTest {
    @Autowired
    private UserSymptomDao userSymptomDao;
    @Autowired
    private DataSource dataSource;

    private UserCalender userCalender1;
    private UserCalender userCalender2;

    private UserSymptom userSymptom1;
    private UserSymptom userSymptom2;

    @Before
    public void setUp() throws Exception {
        userCalender1 = new UserCalender("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 51.4, "07:30:00", "01:30:00", 1.5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, "즐거움", true, true);
        userCalender2 = new UserCalender("slytherin", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 52.5, "13:00:00", "02:00:00", 2.5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null, true, true);

        userSymptom1 = new UserSymptom(null, null, true, true, true, true, true, true, true, true, false, false, false, false, false);
        userSymptom2 = new UserSymptom(null, null, false, false, false, false, false, false, false, false, false, false, false, false, true);

        userSymptom1.setId(userCalender1.getId());
        userSymptom1.setDate(userCalender1.getDate());
        userSymptom2.setId(userCalender2.getId());
        userSymptom2.setDate(userCalender2.getDate());
    }

    @Test
    public void connectIdFromUserCalender() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        assertThat(userSymptom1.getId(), is(userCalender1.getId()));
        assertThat(userSymptom1.getDate(), is(userCalender1.getDate()));
        assertThat(userSymptom2.getId(), is(userCalender2.getId()));
        assertThat(userSymptom2.getDate(), is(userCalender2.getDate()));
    }

    @Test(expected = DuplicateDateException.class)
    public void duplicateIdAndDate() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptom2.setId(userSymptom1.getId());
        userSymptom2.setDate(userSymptom1.getDate());

        userSymptomDao.add(userSymptom1);
        userSymptomDao.add(userSymptom2);
        assertThat(userSymptomDao.getCount(), is(1));
    }

    @Test
    public void addAndGet() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptom1);
        userSymptomDao.add(userSymptom2);
        assertThat(userSymptomDao.getCount(), is(2));

        UserSymptom userSymptomGet1 = userSymptomDao.get(userSymptom1.getId(), userSymptom1.getDate());
        checkSameUserSymptom(userSymptomGet1, userSymptom1);

        UserSymptom userSymptomGet2 = userSymptomDao.get(userSymptom2.getId(), userSymptom2.getDate());
        checkSameUserSymptom(userSymptomGet2, userSymptom2);
    }

    @Test
    public void getGroupByIdOrDate() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptom2.setId(userSymptom1.getId());
        userSymptom2.setDate("2000-01-01");

        userSymptomDao.add(userSymptom1);
        userSymptomDao.add(userSymptom2);
        assertThat(userSymptomDao.getCount(),is(2));
        assertThat(userSymptomDao.getCountEachId(userSymptom1.getId()), is(2));
        assertThat(userSymptomDao.getCountEachIdAndDate(userSymptom1.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date())), is(1));
    }

    @Test
    public void getSymptomById() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptom1);
        userSymptomDao.add(userSymptom2);
        assertThat(userSymptomDao.getCount(),is(2));

        List<UserSymptom> userSymptoms = userSymptomDao.getEachId(userSymptom1.getId());
        assertThat(userSymptoms.size(), is(1));
        for(UserSymptom userSymptom : userSymptoms) {
            checkSameUserSymptom(userSymptom1, userSymptom);
        }
    }

    @Test
    public void getSymptomTrue() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptom2);
        assertThat(userSymptomDao.getCount(), is(1));

        List<String> symptomsGet = userSymptomDao.getSymptomTrue(userSymptomDao.get(userSymptom2.getId(), userSymptom2.getDate()));

        assertThat(symptomsGet.size(), is(3));
        assertThat(symptomsGet.get(2), is("설사"));
    }

    @Test
    public void update() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptom2);
        assertThat(userSymptomDao.getCount(), is(1));

        userSymptom2.setAbdominalBloating(true);
        userSymptomDao.update(userSymptomDao.get(userSymptom2.getId(), userSymptom2.getDate()), "abdominalBloating", userSymptom2.isAbdominalBloating());
        checkSameUserSymptom(userSymptomDao.get(userSymptom2.getId(), userSymptom2.getDate()), userSymptom2);
    }

    private void checkSameUserSymptom(UserSymptom userSymptom1, UserSymptom userSymptom2) {
        assertThat(userSymptom1.getId(), is(userSymptom2.getId()));
        assertThat(userSymptom1.getDate(), is(userSymptom2.getDate()));
        assertThat(userSymptom1.isNone(), is(userSymptom2.isNone()));
        assertThat(userSymptom1.isCramps(), is(userSymptom2.isCramps()));
        assertThat(userSymptom1.isBreastTenderness(), is(userSymptom2.isBreastTenderness()));
        assertThat(userSymptom1.isHeadache(), is(userSymptom2.isHeadache()));
        assertThat(userSymptom1.isAcne(), is(userSymptom2.isAcne()));
        assertThat(userSymptom1.isLumbago(), is(userSymptom2.isLumbago()));
        assertThat(userSymptom1.isNausea(), is(userSymptom2.isNausea()));
        assertThat(userSymptom1.isFatigue(), is(userSymptom2.isFatigue()));
        assertThat(userSymptom1.isAbdominalBloating(), is(userSymptom2.isAbdominalBloating()));
        assertThat(userSymptom1.isDesires(), is(userSymptom2.isDesires()));
        assertThat(userSymptom1.isInsomnia(), is(userSymptom2.isInsomnia()));
        assertThat(userSymptom1.isConstipation(), is(userSymptom2.isConstipation()));
        assertThat(userSymptom1.isDiarrhea(), is(userSymptom2.isDiarrhea()));
    }
}
