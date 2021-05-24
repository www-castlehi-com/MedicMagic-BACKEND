package MedicMagic.userReminder.userReminderList.service;

import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserReminderListService {
    void add(UserReminderListDto userReminderListDto);
    @Transactional(readOnly = true)
    UserReminderListDto get(String id);
    @Transactional(readOnly = true)
    List<UserReminderListDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCountEachId(String id);
    void update(UserReminderListDto userReminderListDto);
}
