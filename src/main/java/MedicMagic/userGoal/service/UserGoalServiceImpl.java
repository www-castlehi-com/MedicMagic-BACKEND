package MedicMagic.userGoal.service;

import MedicMagic.userGoal.dao.UserGoalDao;
import MedicMagic.userGoal.domain.UserGoal;

import java.util.List;

public class UserGoalServiceImpl implements UserGoalService{
    UserGoalDao userGoalDao;

    public void setUserGoalDao(UserGoalDao userGoalDao) {
        this.userGoalDao = userGoalDao;
    }

    @Override
    public void add(UserGoal userGoal) {
        userGoalDao.add(userGoal);
    }

    @Override
    public UserGoal get(String id) {
        return userGoalDao.get(id);
    }

    @Override
    public List<UserGoal> getAll() {
        return userGoalDao.getAll();
    }

    @Override
    public void deleteAll() {
        userGoalDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        userGoalDao.deleteEachId(id);
    }

    @Override
    public void update(String column, Object object, String id) {
        userGoalDao.update(column, object, id);
    }
}
