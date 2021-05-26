package MedicMagic.userSymptom.dao;

import MedicMagic.exception.DuplicateDateException;
import MedicMagic.userCalender.dao.UserCalenderDao;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;
import MedicMagic.userSymptom.dto.UserSymptomDto;
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
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserSymptomDaoTest {
    @Autowired
    private UserCalenderDao userCalenderDao;
    @Autowired
    private UserSymptomDao userSymptomDao;
    @Autowired
    private DataSource dataSource;

    private UserSymptom userSymptom1;
    private UserSymptom userSymptom2;

    private UserSymptomDto userSymptomDto1;
    private UserSymptomDto userSymptomDto2;

    private UserSymptomDto userSymptomDto3;

    @Before
    public void setUp() throws Exception {
        userSymptom1 = new UserSymptom("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), true, true, true, true, true, true, true, true, false, false, false, false, false);
        userSymptom2 = new UserSymptom("hufflepuff", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), false, false, false, false, false, false, false, false, false, false, false, false, true);

        userSymptomDto1 = new UserSymptomDto(userSymptom1);
        userSymptomDto2 = new UserSymptomDto(userSymptom2);

        userSymptomDto3 = new UserSymptomDto("hufflepuff", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "true");
    }

    @Test
    public void addAndGet() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptomDto1);
        userSymptomDao.add(userSymptomDto2);
        assertThat(userSymptomDao.getCount(), is(2));

        checkSameUserSymptom(userSymptomDao.get(userSymptom1.getId(), userSymptom1.getDate()), userSymptomDto1);
        checkSameUserSymptom(userSymptomDao.get(userSymptom2.getId(), userSymptom2.getDate()), userSymptomDto2);
    }

    @Test
    public void getGroupByIdOrDate() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDto2.id = userSymptom1.getId();
        userSymptomDto2.date = "2000-01-01";

        userSymptomDao.add(userSymptomDto1);
        userSymptomDao.add(userSymptomDto2);
        assertThat(userSymptomDao.getCount(),is(2));
        assertThat(userSymptomDao.getCountEachId(userSymptom1.getId()), is(2));
        assertThat(userSymptomDao.getCountEachIdAndDate(userSymptom1.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date())), is(1));
    }

    @Test
    public void getSymptomById() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptomDto1);
        userSymptomDao.add(userSymptomDto2);
        assertThat(userSymptomDao.getCount(),is(2));

        List<UserSymptomDto> userSymptoms = userSymptomDao.getEachId(userSymptom1.getId());
        assertThat(userSymptoms.size(), is(1));
        for(UserSymptomDto userSymptomDto : userSymptoms) {
            checkSameUserSymptom(userSymptomDto1, userSymptomDto);
        }
    }

    @Test
    public void update() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptomDto2);
        assertThat(userSymptomDao.getCount(), is(1));

        userSymptomDto2.abdominalBloating = true;
        userSymptomDao.update(userSymptomDto2);
        checkSameUserSymptom(userSymptomDao.get(userSymptom2.getId(), userSymptom2.getDate()), userSymptomDto2);
    }

    @Test
    public void deleteEachIdAndDate() {
        userSymptomDao.deleteAll();
        assertThat(userSymptomDao.getCount(), is(0));

        userSymptomDao.add(userSymptomDto1);
        assertThat(userSymptomDao.getCount(), is(1));

        userSymptomDao.deleteEachIdAndDate(userSymptom1.getId(), userSymptom2.getDate());
        assertThat(userSymptomDao.getCount(), is(0));
    }

    private void checkSameUserSymptom(UserSymptomDto userSymptomDto1, UserSymptomDto userSymptomDto2) {
        assertThat(userSymptomDto1.id, is(userSymptomDto2.id));
        assertThat(userSymptomDto1.date, is(userSymptomDto2.date));
        assertThat(userSymptomDto1.none, is(userSymptomDto2.none));
        assertThat(userSymptomDto1.cramps, is(userSymptomDto2.cramps));
        assertThat(userSymptomDto1.breastTenderness, is(userSymptomDto2.breastTenderness));
        assertThat(userSymptomDto1.headache, is(userSymptomDto2.headache));
        assertThat(userSymptomDto1.acne, is(userSymptomDto2.acne));
        assertThat(userSymptomDto1.lumbago, is(userSymptomDto2.lumbago));
        assertThat(userSymptomDto1.nausea, is(userSymptomDto2.nausea));
        assertThat(userSymptomDto1.fatigue, is(userSymptomDto2.fatigue));
        assertThat(userSymptomDto1.abdominalBloating, is(userSymptomDto2.abdominalBloating));
        assertThat(userSymptomDto1.desires, is(userSymptomDto2.desires));
        assertThat(userSymptomDto1.insomnia, is(userSymptomDto2.insomnia));
        assertThat(userSymptomDto1.constipation, is(userSymptomDto2.constipation));
        assertThat(userSymptomDto1.diarrhea, is(userSymptomDto2.diarrhea));
    }
}
