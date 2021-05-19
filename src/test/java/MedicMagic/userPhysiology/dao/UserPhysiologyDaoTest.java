package MedicMagic.userPhysiology.dao;

import MedicMagic.userCalender.DuplicateDateException;
import MedicMagic.userPhysiology.LastValueNullException;
import MedicMagic.userPhysiology.domain.UserPhysiology;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserPhysiologyDaoTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserPhysiologyDao userPhysiologyDao;

    private List<UserPhysiology> userPhysiologyList = new ArrayList<>();
    private String endPhysiology;
    private String expectedOvulationDate;
    private String expectedPhysiologyDate;

    @Before
    public void setUp() throws Exception {
        userPhysiologyList = Arrays.asList(
                new UserPhysiology("gryffindor", "2021-05-19", "2021-05-20", "2021-06-04", "2021-06-19"),
                new UserPhysiology("gryffindor", "2021-06-19", "2021-06-20", "2021-07-04", "2021-07-19"),
                new UserPhysiology("gryffindor", "2021-07-19", "2021-07-20", "2021-08-04", "2021-08-19"),
                new UserPhysiology("slyderin", "2021-07-20", "2021-07-21", "2021-08-05", "2021-08-20"),
                new UserPhysiology("slyderin", "2021-08-12", "null", "null", "null")
        );

        endPhysiology = "2021-08-13";
        expectedOvulationDate = "2021-09-05";
        expectedPhysiologyDate = "2021-09-20";
    }

    @Test
    public void addAndGet() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(0));
        assertThat(userPhysiologyDao.getCount(), is(1));
        checkSameUserIdAndStartPhysiology(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(0).getId(), userPhysiologyList.get(0).getStartPhysiology()), userPhysiologyList.get(0));

        userPhysiologyDao.add(userPhysiologyList.get(3));
        assertThat(userPhysiologyDao.getCount(), is(2));
        checkSameUserIdAndStartPhysiology(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(3).getId(), userPhysiologyList.get(3).getStartPhysiology()), userPhysiologyList.get(3));
    }

    @Test
    public void update() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        int count = 1;
        for(UserPhysiology userPhysiology : userPhysiologyList) {
            userPhysiologyDao.add(userPhysiology);
            assertThat(userPhysiologyDao.getCount(), is(count++));
            userPhysiologyDao.update(userPhysiology);
        }

        for(int i = 0; i < userPhysiologyDao.getAll().size(); i++) {
            checkSameUser(userPhysiologyList.get(i), userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(i).getId(), userPhysiologyList.get(i).getStartPhysiology()));
        }
    }

    @Test
    public void getLimit3() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        int count = 1;
        for(UserPhysiology userPhysiology : userPhysiologyList) {
            userPhysiologyDao.add(userPhysiology);
            assertThat(userPhysiologyDao.getCount(), is(count++));
            userPhysiologyDao.update(userPhysiology);
        }

        count = 2;
        for(UserPhysiology userPhysiology : userPhysiologyDao.getEachIdLimit3(userPhysiologyList.get(0).getId())) {
            checkSameUser(userPhysiology, userPhysiologyList.get(count--));
        }
    }

    @Test
    public void getLast() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(3));
        userPhysiologyDao.update(userPhysiologyList.get(3));
        userPhysiologyDao.add(userPhysiologyList.get(4));
        userPhysiologyDao.update(userPhysiologyList.get(4));
        assertThat(userPhysiologyDao.getCount(), is(2));

        checkSameUser(userPhysiologyList.get(4), userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).getId()));
    }

    @Test
    public void addLast() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(3));
        userPhysiologyDao.update(userPhysiologyList.get(3));
        userPhysiologyDao.add(userPhysiologyList.get(4));
        userPhysiologyDao.update(userPhysiologyList.get(4));
        assertThat(userPhysiologyDao.getCount(), is(2));

        UserPhysiology userPhysiology = userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).getId());
        userPhysiology.setEndPhysiology(endPhysiology);
        userPhysiology.setExpectedOvulationDate(expectedOvulationDate);
        userPhysiology.setExpectedPhysiologyDate(expectedPhysiologyDate);

        userPhysiologyDao.update(userPhysiology);
        assertThat(userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).getId()).getEndPhysiology(), is(endPhysiology));
        assertThat(userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).getId()).getExpectedOvulationDate(), is(expectedOvulationDate));
        assertThat(userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).getId()).getExpectedPhysiologyDate(), is(expectedPhysiologyDate));
    }

    @Test(expected = LastValueNullException.class)
    public void lastValueIsNull() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(4));
        userPhysiologyDao.update(userPhysiologyList.get(4));
        assertThat(userPhysiologyDao.getCount(), is(1));

        UserPhysiology userPhysiology = new UserPhysiology(userPhysiologyList.get(4).getId(), "2000-01-01", endPhysiology, expectedOvulationDate, expectedPhysiologyDate);
        userPhysiologyDao.add(userPhysiology);
    }

    @Test
    public void deleteWithStartPhysiology() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(0));
        userPhysiologyDao.update(userPhysiologyList.get(0));
        assertThat(userPhysiologyDao.getCount(), is(1));

        userPhysiologyDao.deleteEachIdAndStartPhysiology(userPhysiologyList.get(0).getId(), userPhysiologyList.get(0).getStartPhysiology());
        assertThat(userPhysiologyDao.getCount(), is(0));
    }

    @Test
    public void deleteWithEndPhsiology() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(0));
        userPhysiologyDao.update(userPhysiologyList.get(0));
        assertThat(userPhysiologyDao.getCount(), is(1));

        userPhysiologyDao.deleteEachIdAndEndPhysiology(userPhysiologyList.get(0).getId(), userPhysiologyList.get(0).getEndPhysiology());
        assertThat(userPhysiologyDao.getCount(), is(0));
    }

    private void checkSameUserIdAndStartPhysiology(UserPhysiology userPhysiology1, UserPhysiology userPhysiology2) {
        assertThat(userPhysiology1.getId(), is(userPhysiology2.getId()));
        assertThat(userPhysiology1.getStartPhysiology(), is(userPhysiology2.getStartPhysiology()));
    }

    private void checkSameUser(UserPhysiology userPhysiology1, UserPhysiology userPhysiology2) {
        assertThat(userPhysiology1.getId(), is(userPhysiology2.getId()));
        assertThat(userPhysiology1.getStartPhysiology(), is(userPhysiology2.getStartPhysiology()));
        assertThat(userPhysiology1.getEndPhysiology(), is(nullCheck(userPhysiology2.getEndPhysiology())));
        assertThat(userPhysiology1.getExpectedOvulationDate(), is(nullCheck(userPhysiology2.getExpectedOvulationDate())));
        assertThat(userPhysiology1.getExpectedPhysiologyDate(), is(nullCheck(userPhysiology2.getExpectedPhysiologyDate())));
    }

    private String nullCheck(String object) {
        if(object == null) {
            return "null";
        }
        return object;
    }
}
