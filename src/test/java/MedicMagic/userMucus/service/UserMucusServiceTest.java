package MedicMagic.userMucus.service;

import MedicMagic.userMucus.dao.UserMucusDao;
import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userMucus.dto.UserMucusDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserMucusServiceTest {
    @Autowired
    UserMucusService testUserMucusService;

    @Test(expected = TransientDataAccessResourceException.class)
    public void readOnlyTransactionAttribute() {
        testUserMucusService.getAll();
    }

    static class TestUserMucusServiceImpl extends UserMucusServiceImpl {
        @Override
        public List<UserMucusDto> getAll() {
            for(UserMucusDto userMucusDto : super.getAll()) {
                super.update(userMucusDto);
            }
            return null;
        }
    }
}
