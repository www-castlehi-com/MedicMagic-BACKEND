package MedicMagic.userPhysiology.service;

import MedicMagic.userPhysiology.domain.UserPhysiology;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserPhysiologyServiceTest {
    @Autowired
    UserPhysiologyService testUserPhysiologyService;
    @Autowired
    UserPhysiologyService userPhysiologyService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        userPhysiologyService.deleteAll();
        assertThat(userPhysiologyService.getAll().size(), is(0));

        userPhysiologyService.add(new UserPhysiologyDto("gryffindor", "2021-05-19", "2021-05-20", "2021-06-04", "2021-06-19"));

        testUserPhysiologyService.getAll();
    }

    static class TestUserPhysiologyServiceImpl extends UserPhysiologyServiceImpl {
        @Override
        public List<UserPhysiologyDto> getAll() {
            for(UserPhysiologyDto userPhysiologyDto : super.getAll()) {
                super.update(userPhysiologyDto);
            }
            return null;
        }
    }
}
