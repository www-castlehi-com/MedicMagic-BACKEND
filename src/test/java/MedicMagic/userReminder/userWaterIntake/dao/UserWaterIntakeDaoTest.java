package MedicMagic.userReminder.userWaterIntake.dao;

import MedicMagic.userReminder.userWaterIntake.domain.UserWaterIntake;
import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserWaterIntakeDaoTest {
    @Autowired
    UserWaterIntakeDao userWaterIntakeDao;
    @Autowired
    DataSource dataSource;

    UserWaterIntake userWaterIntake1;
    UserWaterIntake userWaterIntake2;

    UserWaterIntakeDto userWaterIntakeDto1;
    UserWaterIntakeDto userWaterIntakeDto2;

    UserWaterIntakeDto userWaterIntakeDto3;

    @Before
    public void setUp() throws Exception {
        userWaterIntake1 = new UserWaterIntake("gryffindor", 1.5, "02:00:00");
        userWaterIntake2 = new UserWaterIntake("hufflepuff", 2.0, null);

        userWaterIntakeDto1 = new UserWaterIntakeDto(userWaterIntake1);
        userWaterIntakeDto2 = new UserWaterIntakeDto(userWaterIntake2);

        userWaterIntakeDto3 = new UserWaterIntakeDto("slyderin", "2.0", "null");
    }

    @Test
    public void addAndGet() {
        userWaterIntakeDao.deleteAll();
        assertThat(userWaterIntakeDao.getCount(), is(0));

        userWaterIntakeDao.add(userWaterIntakeDto1);
        assertThat(userWaterIntakeDao.getCount(), is(1));

        userWaterIntakeDao.add(userWaterIntakeDto2);
        assertThat(userWaterIntakeDao.getCount(), is(2));

        checkSameUserWaterIntake(userWaterIntakeDao.get(userWaterIntakeDto1.id), userWaterIntakeDto1);
        checkSameUserWaterIntake(userWaterIntakeDao.get(userWaterIntakeDto2.id), userWaterIntakeDto2);
    }

    @Test
    public void stringTypeAddAndGet() {
        userWaterIntakeDao.deleteAll();
        assertThat(userWaterIntakeDao.getCount(), is(0));

        userWaterIntakeDao.add(userWaterIntakeDto3);
        assertThat(userWaterIntakeDao.getCount(), is(1));

        assertThat(userWaterIntakeDao.get(userWaterIntakeDto3.id).cups ,is(userWaterIntakeDto3.cups));
        assertThat(userWaterIntakeDao.get(userWaterIntakeDto3.id).waterTime, is("22:00:00"));
    }

    private void checkSameUserWaterIntake(UserWaterIntakeDto userWaterIntakeDto1, UserWaterIntakeDto userWaterIntakeDto2) {
        assertThat(userWaterIntakeDto1.id, is(userWaterIntakeDto2.id));
        assertThat(userWaterIntakeDto1.cups, is(userWaterIntakeDto2.cups));
        assertThat(userWaterIntakeDto1.waterTime, is(userWaterIntakeDto2.waterTime));
    }
}
