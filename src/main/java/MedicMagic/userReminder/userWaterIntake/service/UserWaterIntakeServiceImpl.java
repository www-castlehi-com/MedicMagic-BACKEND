package MedicMagic.userReminder.userWaterIntake.service;

import MedicMagic.userReminder.userWaterIntake.dao.UserWaterIntakeDao;
import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;

import java.util.List;

public class UserWaterIntakeServiceImpl implements UserWaterIntakeService{
    private UserWaterIntakeDao userWaterIntakeDao;

    public void setUserWaterIntakeDao(UserWaterIntakeDao userWaterIntakeDao) {
        this.userWaterIntakeDao = userWaterIntakeDao;
    }

    @Override
    public void add(UserWaterIntakeDto userWaterIntakeDto) {
        this.userWaterIntakeDao.add(userWaterIntakeDto);
    }

    @Override
    public UserWaterIntakeDto get(String id) {
        return this.userWaterIntakeDao.get(id);
    }

    @Override
    public List<UserWaterIntakeDto> getAll() {
        return this.userWaterIntakeDao.getAll();
    }

    @Override
    public void deleteAll() {
        this.userWaterIntakeDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        this.userWaterIntakeDao.deleteEachId(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userWaterIntakeDao.getCountEachId(id);
    }

    @Override
    public void update(UserWaterIntakeDto userWaterIntakeDto) {
        this.userWaterIntakeDao.update(userWaterIntakeDto);
    }
}
