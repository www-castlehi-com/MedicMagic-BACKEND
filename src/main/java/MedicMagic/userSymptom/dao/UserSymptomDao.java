package MedicMagic.userSymptom.dao;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;
import MedicMagic.userSymptom.dto.UserSymptomDto;

import java.util.List;

public interface UserSymptomDao {
    void add(UserSymptomDto userSymptomDto);
    UserSymptomDto get(String id, String date);
    List<UserSymptomDto> getAll();
    List<UserSymptomDto> getEachId(String id);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    int getCount();
    int getCountEachId(String id);
    int getCountEachIdAndDate(String id, String date);
    void update(UserSymptomDto userSymptomDto);
}
