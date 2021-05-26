package MedicMagic.userSymptom.service;

import MedicMagic.userSymptom.domain.UserSymptom;
import MedicMagic.userSymptom.dto.UserSymptomDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserSymptomServiceTest {
    @Autowired
    UserSymptomService testUserSymptomService;
    @Autowired
    UserSymptomService userSymptomService;

    @Test(expected = TransientDataAccessException.class)
    public void readOnlyTransactionAttribute() {
        userSymptomService.deleteAll();
        assertThat(userSymptomService.getAll().size(), is(0));

        userSymptomService.add(new UserSymptomDto(new UserSymptom("gryffindor", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), true, true, true, true, true, true, true, true, false, false, false, false, false)));
        assertThat(userSymptomService.getAll().size(), is(1));

        testUserSymptomService.getAll();
    }

    static class TestUserSymptomServiceImpl extends UserSymptomServiceImpl {
        @Override
        public List<UserSymptomDto> getAll() {
            for(UserSymptomDto userSymptomDto : super.getAll()) {
                super.update(userSymptomDto);
            }
            return null;
        }
    }
}
