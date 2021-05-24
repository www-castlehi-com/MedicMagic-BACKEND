package MedicMagic.userReminder.userBirthControlPills.service;

import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserBirthControlPillsService {
    void add(UserBirthControlPillsDto userBirthControlPillsDto);
    @Transactional(readOnly = true)
    UserBirthControlPillsDto get(String id);
    @Transactional(readOnly = true)
    List<UserBirthControlPillsDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachId(String id);
    void update(UserBirthControlPillsDto userBirthControlPillsDto);
}
