package MedicMagic.userCalender.dao;

import MedicMagic.userCalender.domain.UserCalender;

import java.util.List;

public interface UserCalenderDao {
    void add(UserCalender userCalender);
    UserCalender get(String id, String date);
    List<UserCalender> getAll();
    void deleteAll();
    int getCount();
    int eachIdGetCount();
    void update(UserCalender userCalender);
}
