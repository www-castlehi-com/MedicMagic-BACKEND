package MedicMagic.userReminder.userWaterIntake.service;

import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserWaterIntakeService {
    void add(UserWaterIntakeDto userWaterIntakeDto);
    @Transactional(readOnly = true)
    UserWaterIntakeDto get(String id);
    @Transactional(readOnly = true)
    List<UserWaterIntakeDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachId(String id);
    void update(UserWaterIntakeDto userWaterIntakeDto);
}
