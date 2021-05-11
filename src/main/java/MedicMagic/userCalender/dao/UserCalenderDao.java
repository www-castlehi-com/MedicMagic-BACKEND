package MedicMagic.userCalender.dao;

import MedicMagic.userCalender.domain.UserCalender;

import java.util.List;

public interface UserCalenderDao {
    void add(UserCalender userCalender);
    UserCalender get(String id, String date);
    List<UserCalender> getAll();
    List<UserCalender> getEachId(String id);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    int getCount();
    int getCountEachId(String id);
    void update(UserCalender userCalender, String column, Object object);
}
