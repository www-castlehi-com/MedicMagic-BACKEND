package MedicMagic.userReminder.userBirthControlPills.service;

import MedicMagic.userReminder.userBirthControlPills.dao.UserBirthControlPillsDao;
import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;

import java.util.List;

public class UserBirthControlPillsServiceImpl implements UserBirthControlPillsService{
    private UserBirthControlPillsDao userBirthControlPillsDao;

    public void setUserBirthControlPillsDao(UserBirthControlPillsDao userBirthControlPillsDao) {
        this.userBirthControlPillsDao = userBirthControlPillsDao;
    }

    @Override
    public void add(UserBirthControlPillsDto userBirthControlPillsDto) {
        this.userBirthControlPillsDao.add(userBirthControlPillsDto);
    }

    @Override
    public UserBirthControlPillsDto get(String id) {
        return this.userBirthControlPillsDao.get(id);
    }

    @Override
    public List<UserBirthControlPillsDto> getAll() {
        return this.userBirthControlPillsDao.getAll();
    }

    @Override
    public void deleteAll() {
        this.userBirthControlPillsDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        this.userBirthControlPillsDao.deleteEachId(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userBirthControlPillsDao.getCountEachId(id);
    }

    @Override
    public void update(UserBirthControlPillsDto userBirthControlPillsDto) {
        this.userBirthControlPillsDao.update(userBirthControlPillsDto);
    }
}
