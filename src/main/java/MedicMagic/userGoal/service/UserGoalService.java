package MedicMagic.userGoal.service;

import MedicMagic.userGoal.domain.UserGoal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserGoalService {
    void add(UserGoal userGoal);
    @Transactional(readOnly = true)
    UserGoal get(String id);
    @Transactional(readOnly = true)
    List<UserGoal> getAll();
    void deleteAll();
    void deleteEachId(String id);
    void update(String column, Object object, String id);
}
