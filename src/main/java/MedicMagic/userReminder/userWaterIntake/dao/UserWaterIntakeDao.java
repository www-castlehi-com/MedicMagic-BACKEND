package MedicMagic.userReminder.userWaterIntake.dao;

import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;

import java.util.List;

public interface UserWaterIntakeDao {
    void add(UserWaterIntakeDto userWaterIntakeDto);
    UserWaterIntakeDto get(String id);
    List<UserWaterIntakeDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    int getCountEachId(String id);
    void update(UserWaterIntakeDto userWaterIntakeDto);
}
