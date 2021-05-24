package MedicMagic.userReminder.userExercise.dao;

import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;

import java.util.List;

public interface UserExerciseDao {
    void add(UserExerciseDto userExerciseDto);
    UserExerciseDto get(String id);
    List<UserExerciseDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    int getCountEachId(String id);
    void update(UserExerciseDto userExerciseDto);
}
