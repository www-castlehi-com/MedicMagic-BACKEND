package MedicMagic.userCalender.dao;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;

import java.util.List;

public interface UserCalenderDao {
    void add(UserCalenderDto userCalenderDto);
    UserCalenderDto get(String id, String date);
    List<UserCalenderDto> getAll();
    List<UserCalenderDto> getEachId(String id);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    int getCount();
    int getCountEachId(String id);
    int getCountEachIdAndDate(String id, String date);
    void update(UserCalenderDto userCalenderDto);
}
