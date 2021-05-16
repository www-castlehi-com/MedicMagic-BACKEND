package MedicMagic.userReminder.dao;

import MedicMagic.userReminder.domain.UserReminder;

import java.util.List;

public interface UserReminderDao {
    void add(String id);
    UserReminder get(String id);
    List<UserReminder> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    void update(UserReminder userReminder);
}
