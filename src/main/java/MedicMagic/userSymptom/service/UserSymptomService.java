package MedicMagic.userSymptom.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;
import MedicMagic.userSymptom.dto.UserSymptomDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserSymptomService {
    void add(UserSymptomDto userSymptomDto);
    @Transactional(readOnly = true)
    UserSymptomDto get(String id, String date);
    @Transactional(readOnly = true)
    List<UserSymptomDto> getAll();
    @Transactional(readOnly = true)
    List<UserSymptomDto> getEachId(String id);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    @Transactional(readOnly = true)
    int getCountEachIdAndDate(String id, String date);
    void update(UserSymptomDto userSymptomDto);
}
