package MedicMagic.userPhysiology.service;

import MedicMagic.userPhysiology.domain.UserPhysiology;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserPhysiologyServiceTest {
    @Autowired
    UserPhysiologyService testUserPhysiologyService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        testUserPhysiologyService.getAll();
    }

    static class TestUserPhysiologyServiceImpl extends UserPhysiologyServiceImpl {
        @Override
        public List<UserPhysiology> getAll() {
            for(UserPhysiology userPhysiology : super.getAll()) {
                super.update(userPhysiology);
            }
            return null;
        }
    }
}
