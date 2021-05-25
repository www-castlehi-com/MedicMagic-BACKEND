package MedicMagic.userReminder.userExercise.dao;

import MedicMagic.userReminder.userExercise.domain.UserExercise;
import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;
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
public class UserExerciseDaoTest {
    @Autowired
    UserExerciseDao userExerciseDao;
    @Autowired
    DataSource dataSource;

    UserExercise userExercise1;
    UserExercise userExercise2;

    UserExerciseDto userExerciseDto1;
    UserExerciseDto userExerciseDto2;

    UserExerciseDto userExerciseDto3;

    @Before
    public void setUp() throws Exception {
        userExercise1 = new UserExercise("gryffindor", 3.0);
        userExercise2 = new UserExercise("hufflepuff", 0.0);

        userExerciseDto1 = new UserExerciseDto(userExercise1);
        userExerciseDto2 = new UserExerciseDto(userExercise2);

        userExerciseDto3 = new UserExerciseDto("slyderin", "0.0");
    }

    @Test
    public void addAndGet() {
        userExerciseDao.deleteAll();
        assertThat(userExerciseDao.getCount(), is(0));

        userExerciseDao.add(userExerciseDto1);
        assertThat(userExerciseDao.getCount(), is(1));

        userExerciseDao.add(userExerciseDto2);
        assertThat(userExerciseDao.getCount(), is(2));

        checkSameUserExercise(userExerciseDao.get(userExerciseDto1.id), userExerciseDto1);
        checkSameUserExercise(userExerciseDao.get(userExerciseDto2.id), userExerciseDto2);
    }

    @Test
    public void stringTypeAddAndGet() {
        userExerciseDao.deleteAll();
        assertThat(userExerciseDao.getCount(), is(0));

        userExerciseDao.add(userExerciseDto3);
        assertThat(userExerciseDao.getCount(), is(1));

        assertThat(userExerciseDao.get(userExerciseDto3.id).exerciseHour, is(0.0));
    }

    private void checkSameUserExercise(UserExerciseDto userExerciseDto1, UserExerciseDto userExerciseDto2) {
        assertThat(userExerciseDto1.id, is(userExerciseDto2.id));
        assertThat(userExerciseDto1.exerciseHour, is(userExerciseDto2.exerciseHour));
    }
}
