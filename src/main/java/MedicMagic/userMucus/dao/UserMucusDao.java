package MedicMagic.userMucus.dao;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userMucus.dto.UserMucusDto;

import java.util.List;

public interface UserMucusDao {
    void add(UserMucusDto userMucusDto);
    UserMucusDto get(String id, String date);
    List<UserMucusDto> getAll();
    List<UserMucusDto> getEachId(String id);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    int getCount();
    int getCountEachId(String id);
    int getCountEachIdAndDate(String id, String date);
    void update(UserMucusDto userMucusDto);
}
