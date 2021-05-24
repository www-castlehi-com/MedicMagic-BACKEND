package MedicMagic.userReminder.userHospital.dao;

import MedicMagic.userReminder.userHospital.domain.UserHospital;
import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserHospitalDaoTest {
    @Autowired
    UserHospitalDao userHospitalDao;
    @Autowired
    DataSource dataSource;

    UserHospital userHospital1;
    UserHospital userHospital2;

    UserHospitalDto userHospitalDto1;
    UserHospitalDto userHospitalDto2;

    UserHospitalDto userHospitalDto3;

    @Before
    public void setUp() throws Exception {
        userHospital1 = new UserHospital("gryffindor", "2021-05-05", "15:00:00");
        userHospital2 = new UserHospital("hufflepuff", null, null);

        userHospitalDto1 = new UserHospitalDto(userHospital1);
        userHospitalDto2 = new UserHospitalDto(userHospital2);

        userHospitalDto3 = new UserHospitalDto("slyderin", "null", "null");
    }

    @Test
    public void addAndGet() {
        userHospitalDao.deleteAll();
        assertThat(userHospitalDao.getCount(), is(0));

        userHospitalDao.add(userHospitalDto1);
        assertThat(userHospitalDao.getCount(), is(1));

        userHospitalDao.add(userHospitalDto2);
        assertThat(userHospitalDao.getCount(), is(2));

        checkSameUserHospital(userHospitalDao.get(userHospitalDto1.id), userHospitalDto1);
        checkSameUserHospital(userHospitalDao.get(userHospitalDto2.id), userHospitalDto2);
    }

    @Test
    public void stringTypeAddAndGet() {
        userHospitalDao.deleteAll();
        assertThat(userHospitalDao.getCount(), is(0));

        userHospitalDao.add(userHospitalDto3);
        assertThat(userHospitalDao.getCount(), is(1));

        assertThat(userHospitalDao.get(userHospitalDto3.id).hospitalDate, is(nullValue()));
        assertThat(userHospitalDao.get(userHospitalDto3.id).hospitalTime, is("18:00:00"));
    }

    private void checkSameUserHospital(UserHospitalDto userHospitalDto1, UserHospitalDto userHospitalDto2) {
        assertThat(userHospitalDto1.id, is(userHospitalDto2.id));
        assertThat(userHospitalDto1.hospitalDate, is(userHospitalDto2.hospitalDate));
        assertThat(userHospitalDto1.hospitalTime, is(userHospitalDto2.hospitalTime));
    }
}
