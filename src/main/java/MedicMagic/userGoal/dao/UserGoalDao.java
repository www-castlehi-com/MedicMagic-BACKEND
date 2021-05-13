package MedicMagic.userGoal.dao;

import MedicMagic.userGoal.domain.UserGoal;

import java.util.List;

public interface UserGoalDao {
    void add(UserGoal userGoal);
    UserGoal get(String id);
    List<UserGoal> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    void update(String column, Object object, String id);
}
