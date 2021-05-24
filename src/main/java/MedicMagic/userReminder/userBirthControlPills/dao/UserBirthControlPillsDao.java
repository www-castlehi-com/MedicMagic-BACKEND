package MedicMagic.userReminder.userBirthControlPills.dao;

import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;

import java.util.List;

public interface UserBirthControlPillsDao {
    void add(UserBirthControlPillsDto userBirthControlPillsDto);
    UserBirthControlPillsDto get(String id);
    List<UserBirthControlPillsDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    int getCountEachId(String id);
    void update(UserBirthControlPillsDto userBirthControlPillsDto);
}
