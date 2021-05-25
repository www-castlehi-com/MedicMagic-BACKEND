package MedicMagic.userReminder.userSleep.service;

import MedicMagic.userReminder.userSleep.dao.UserSleepDao;
import MedicMagic.userReminder.userSleep.dto.UserSleepDto;

import java.util.List;

public class UserSleepServiceImpl implements UserSleepService{
    private UserSleepDao userSleepDao;

    public void setUserSleepDao(UserSleepDao userSleepDao) {
        this.userSleepDao = userSleepDao;
    }

    @Override
    public void add(UserSleepDto userSleepDto) {
        this.userSleepDao.add(userSleepDto);
    }

    @Override
    public UserSleepDto get(String id) {
        return this.userSleepDao.get(id);
    }

    @Override
    public List<UserSleepDto> getAll() {
        return this.userSleepDao.getAll();
    }

    @Override
    public void deleteAll() {
        this.userSleepDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        this.userSleepDao.deleteEachId(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userSleepDao.getCountEachId(id);
    }

    @Override
    public void update(UserSleepDto userSleepDto) {
        this.userSleepDao.update(userSleepDto);
    }
}
