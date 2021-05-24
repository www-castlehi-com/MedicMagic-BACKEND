package MedicMagic.userReminder.userReminderList.dao;

import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;

import java.util.List;

public interface UserReminderListDao {
    void add(UserReminderListDto userReminderListDto);
    UserReminderListDto get(String id);
    List<UserReminderListDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    int getCountEachId(String id);
    void update(UserReminderListDto userReminderListDto);
}
