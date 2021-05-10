package MedicMagic.userSymptom.dao;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;

import java.util.List;

public interface UserSymptomDao {
    void add(UserSymptom userSymptom);
    UserSymptom get(String id, String date);
    List<UserSymptom> getAll();
    List<UserSymptom> getEachId(String id);
    List<String> getSymptomTrue(UserSymptom userSymptom);
    void deleteAll();
    int getCount();
    int getCountEachId(String id);
    int getCountEachIdAndDate(String id, String date);
    void update(UserSymptom userSymptom, String column, boolean object);
    void updateUserCalenderIfSymptomIsFalse(UserSymptom userSymptom, UserCalender userCalender);
}
