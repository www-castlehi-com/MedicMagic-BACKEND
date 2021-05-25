package MedicMagic.userReminder.userSleep.dao;

import MedicMagic.userReminder.userSleep.dto.UserSleepDto;

import java.util.List;

public interface UserSleepDao {
    void add(UserSleepDto userSleepDto);
    UserSleepDto get(String id);
    List<UserSleepDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    int getCountEachId(String id);
    void update(UserSleepDto userSleepDto);
}
