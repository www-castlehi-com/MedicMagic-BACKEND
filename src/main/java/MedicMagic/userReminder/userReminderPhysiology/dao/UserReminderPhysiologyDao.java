package MedicMagic.userReminder.userReminderPhysiology.dao;

import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;

import java.util.List;

public interface UserReminderPhysiologyDao {
    void add(UserReminderPhysiologyDto userPhysiologyDto);
    UserReminderPhysiologyDto get(String id);
    List<UserReminderPhysiologyDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    int getCountEachId(String id);
    void update(UserReminderPhysiologyDto userPhysiologyDto);
}
