package MedicMagic.userReminder.userExercise.service;

import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserExerciseService {
    void add(UserExerciseDto userExerciseDto);
    @Transactional(readOnly = true)
    UserExerciseDto get(String id);
    @Transactional(readOnly = true)
    List<UserExerciseDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachId(String id);
    void update(UserExerciseDto userExerciseDto);
}
