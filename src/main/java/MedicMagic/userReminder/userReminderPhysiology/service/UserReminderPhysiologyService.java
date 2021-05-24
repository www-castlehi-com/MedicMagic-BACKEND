package MedicMagic.userReminder.userReminderPhysiology.service;

import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserReminderPhysiologyService {
    void add(UserReminderPhysiologyDto userPhysiologyDto);
    @Transactional(readOnly = true)
    UserReminderPhysiologyDto get(String id);
    @Transactional(readOnly = true)
    List<UserReminderPhysiologyDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachId(String id);
    void update(UserReminderPhysiologyDto userPhysiologyDto);
}
