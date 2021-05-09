package MedicMagic.userSymptom.service;

import MedicMagic.userSymptom.domain.UserSymptom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserSymptomServiceTest {
    @Autowired
    UserSymptomService testUserSymptomService;

    List<UserSymptom> userSymptoms;

    @Before
    public void setUp() throws Exception {
        userSymptoms = Arrays.asList(
                new UserSymptom()
        );
    }

    @Test(expected = TransientDataAccessException.class)
    public void readOnlyTransactionAttribute() { testUserSymptomService.getAll(); }

    static class TestUserSymptomServiceImpl extends UserSymptomServiceImpl {
        @Override
        public List<UserSymptom> getAll() {
            for(UserSymptom userSymptom : super.getAll()) {
                super.update(userSymptom, "none", userSymptom.isNone());
            }
            return null;
        }
    }
}
