package MedicMagic.userReminder.userExercise.service;

import MedicMagic.userReminder.userExercise.dao.UserExerciseDao;
import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;

import java.util.List;

public class UserExerciseServiceImpl implements UserExerciseService{
    private UserExerciseDao userExerciseDao;

    public void setUserExerciseDao(UserExerciseDao userExerciseDao) {
        this.userExerciseDao = userExerciseDao;
    }

    @Override
    public void add(UserExerciseDto userExerciseDto) {
        this.userExerciseDao.add(userExerciseDto);
    }

    @Override
    public UserExerciseDto get(String id) {
        return this.userExerciseDao.get(id);
    }

    @Override
    public List<UserExerciseDto> getAll() {
        return this.userExerciseDao.getAll();
    }

    @Override
    public void deleteAll() {
        this.userExerciseDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        this.userExerciseDao.deleteEachId(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userExerciseDao.getCountEachId(id);
    }

    @Override
    public void update(UserExerciseDto userExerciseDto) {
        this.userExerciseDao.update(userExerciseDto);
    }
}
