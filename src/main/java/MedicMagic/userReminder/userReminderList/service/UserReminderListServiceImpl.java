package MedicMagic.userReminder.userReminderList.service;

import MedicMagic.userReminder.userReminderList.dao.UserReminderListDao;
import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;

import java.util.List;

public class UserReminderListServiceImpl implements UserReminderListService{
    UserReminderListDao userReminderListDao;

    public void setUserReminderListDao(UserReminderListDao userReminderListDao) {
        this.userReminderListDao = userReminderListDao;
    }

    @Override
    public void add(UserReminderListDto userReminderListDto) {
        this.userReminderListDao.add(userReminderListDto);
    }

    @Override
    public UserReminderListDto get(String id) {
        return this.userReminderListDao.get(id);
    }

    @Override
    public List<UserReminderListDto> getAll() {
        return this.userReminderListDao.getAll();
    }

    @Override
    public void deleteAll() {
        this.userReminderListDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        this.userReminderListDao.deleteEachId(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userReminderListDao.getCountEachId(id);
    }

    @Override
    public void update(UserReminderListDto userReminderListDto) {
        this.userReminderListDao.update(userReminderListDto);
    }
}
