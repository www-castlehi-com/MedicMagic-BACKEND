package MedicMagic.userReminder.service;

import MedicMagic.userReminder.domain.UserReminder;
import MedicMagic.userReminder.dto.UserReminderDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserReminderService {
    void add(String id);
    @Transactional(readOnly = true)
    UserReminder get(String id);
    @Transactional(readOnly = true)
    List<UserReminder> getAll();
    void deleteAll();
    void deleteEachId(String id);
    void update(UserReminder userReminder);
    UserReminderDto conveyUserReminder(String id);
}
