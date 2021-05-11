package MedicMagic.userMucus.dao;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.domain.UserMucus;

import java.util.List;

public interface UserMucusDao {
    void add(UserMucus userMucus);
    UserMucus get(String id, String date);
    List<UserMucus> getAll();
    List<UserMucus> getEachId(String id);
    List<String> getMucusTrue(UserMucus userMucus);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    int getCount();
    int getCountEachId(String id);
    int getCountEachIdAndDate(String id, String date);
    void update(UserMucus userMucus, String column, boolean object);
    void updateUserCalenderIfMucusIsFalse(UserMucus userMucus, UserCalender userCalender);
}
