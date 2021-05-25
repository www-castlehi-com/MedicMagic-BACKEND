package MedicMagic.userReminder.userSleep.service;

import MedicMagic.userReminder.userSleep.dto.UserSleepDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserSleepService {
    void add(UserSleepDto userSleepDto);
    @Transactional(readOnly = true)
    UserSleepDto get(String id);
    @Transactional(readOnly = true)
    List<UserSleepDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachId(String id);
    void update(UserSleepDto userSleepDto);
}
