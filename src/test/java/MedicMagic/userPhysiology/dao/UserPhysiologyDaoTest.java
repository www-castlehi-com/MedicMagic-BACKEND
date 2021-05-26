package MedicMagic.userPhysiology.dao;

import MedicMagic.exception.LastValueNullException;
import MedicMagic.userPhysiology.domain.UserPhysiology;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;
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

    private List<UserPhysiologyDto> userPhysiologyList = new ArrayList<>();
    private String endPhysiology;
    private String expectedOvulationDate;
    private String expectedPhysiologyDate;

    @Before
    public void setUp() throws Exception {
        userPhysiologyList = Arrays.asList(
                new UserPhysiologyDto("gryffindor", "2021-05-19", "2021-05-20", "2021-06-04", "2021-06-19"),
                new UserPhysiologyDto("gryffindor", "2021-06-19", "2021-06-20", "2021-07-04", "2021-07-19"),
                new UserPhysiologyDto("gryffindor", "2021-07-19", "2021-07-20", "2021-08-04", "2021-08-19"),
                new UserPhysiologyDto("slyderin", "2021-07-20", "2021-07-21", "2021-08-05", "2021-08-20"),
                new UserPhysiologyDto("slyderin", "2021-08-12", "null", "null", "null")
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
        checkSameUserIdAndStartPhysiology(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(0).id, userPhysiologyList.get(0).startPhysiology), userPhysiologyList.get(0));

        userPhysiologyDao.add(userPhysiologyList.get(3));
        assertThat(userPhysiologyDao.getCount(), is(2));
        checkSameUserIdAndStartPhysiology(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(3).id, userPhysiologyList.get(3).startPhysiology), userPhysiologyList.get(3));
    }

    @Test
    public void update() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        int count = 1;
        for(UserPhysiologyDto userPhysiologyDto : userPhysiologyList) {
            userPhysiologyDao.add(userPhysiologyDto);
            assertThat(userPhysiologyDao.getCount(), is(count++));
//            userPhysiologyDao.update(userPhysiologyDto);
        }

        for(int i = 0; i < userPhysiologyDao.getAll().size(); i++) {
            System.out.println(userPhysiologyList.get(i).startPhysiology);
            System.out.println(userPhysiologyList.get(i).endPhysiology);
            System.out.println(userPhysiologyList.get(i).expectedPhysiologyDate);
            System.out.println(userPhysiologyList.get(i).expectedOvulationDate);


            System.out.println(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(i).id, userPhysiologyList.get(i).startPhysiology).startPhysiology);
            System.out.println(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(i).id, userPhysiologyList.get(i).startPhysiology).endPhysiology);
            System.out.println(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(i).id, userPhysiologyList.get(i).startPhysiology).expectedOvulationDate);
            System.out.println(userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(i).id, userPhysiologyList.get(i).startPhysiology).expectedPhysiologyDate);

            checkSameUser(userPhysiologyList.get(i), userPhysiologyDao.getEachIdAndStartPhysiology(userPhysiologyList.get(i).id, userPhysiologyList.get(i).startPhysiology));
        }
    }

    @Test
    public void getLimit3() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        int count = 1;
        for(UserPhysiologyDto userPhysiologyDto : userPhysiologyList) {
            userPhysiologyDao.add(userPhysiologyDto);
            assertThat(userPhysiologyDao.getCount(), is(count++));
            userPhysiologyDao.update(userPhysiologyDto);
        }

        count = 2;
        for(UserPhysiologyDto userPhysiologyDto : userPhysiologyDao.getEachIdLimit3(userPhysiologyList.get(0).id)) {
            checkSameUser(userPhysiologyDto, userPhysiologyList.get(count--));
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

        checkSameUser(userPhysiologyList.get(4), userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).id));
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

        UserPhysiologyDto userPhysiologyDto = userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).id);
        userPhysiologyDto.endPhysiology = endPhysiology;
        userPhysiologyDto.expectedOvulationDate = expectedOvulationDate;
        userPhysiologyDto.expectedPhysiologyDate = expectedPhysiologyDate;

        userPhysiologyDao.update(userPhysiologyDto);
        assertThat(userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).id).endPhysiology, is(endPhysiology));
        assertThat(userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).id).expectedOvulationDate, is(expectedOvulationDate));
        assertThat(userPhysiologyDao.getLastEachId(userPhysiologyList.get(3).id).expectedPhysiologyDate, is(expectedPhysiologyDate));
    }

    @Test(expected = LastValueNullException.class)
    public void lastValueIsNull() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(4));
        userPhysiologyDao.update(userPhysiologyList.get(4));
        assertThat(userPhysiologyDao.getCount(), is(1));

        UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userPhysiologyList.get(4).id, "2000-01-01", endPhysiology, expectedOvulationDate, expectedPhysiologyDate);
        userPhysiologyDao.add(userPhysiologyDto);
    }

    @Test
    public void deleteWithStartPhysiology() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(0));
        userPhysiologyDao.update(userPhysiologyList.get(0));
        assertThat(userPhysiologyDao.getCount(), is(1));

        userPhysiologyDao.deleteEachIdAndStartPhysiology(userPhysiologyList.get(0).id, userPhysiologyList.get(0).startPhysiology);
        assertThat(userPhysiologyDao.getCount(), is(0));
    }

    @Test
    public void deleteWithEndPhysiology() {
        userPhysiologyDao.deleteAll();
        assertThat(userPhysiologyDao.getCount(), is(0));

        userPhysiologyDao.add(userPhysiologyList.get(0));
        userPhysiologyDao.update(userPhysiologyList.get(0));
        assertThat(userPhysiologyDao.getCount(), is(1));

        userPhysiologyDao.deleteEachIdAndEndPhysiology(userPhysiologyList.get(0).id, userPhysiologyList.get(0).endPhysiology);
        assertThat(userPhysiologyDao.getCount(), is(0));
    }

    private void checkSameUserIdAndStartPhysiology(UserPhysiologyDto userPhysiologyDto1, UserPhysiologyDto userPhysiologyDto2) {
        assertThat(userPhysiologyDto1.id, is(userPhysiologyDto2.id));
        assertThat(userPhysiologyDto1.startPhysiology, is(userPhysiologyDto2.startPhysiology));
    }

    private void checkSameUser(UserPhysiologyDto userPhysiologyDto1, UserPhysiologyDto userPhysiologyDto2) {
        assertThat(userPhysiologyDto1.id, is(userPhysiologyDto2.id));
        assertThat(userPhysiologyDto1.startPhysiology, is(userPhysiologyDto2.startPhysiology));
        assertThat(userPhysiologyDto1.endPhysiology, is(userPhysiologyDto2.endPhysiology));
        assertThat(userPhysiologyDto1.expectedOvulationDate, is(userPhysiologyDto2.expectedOvulationDate));
        assertThat(userPhysiologyDto1.expectedPhysiologyDate, is(userPhysiologyDto2.expectedPhysiologyDate));
    }

    private String nullCheck(String object) {
        if(object == null) {
            return "null";
        }
        return object;
    }
}
