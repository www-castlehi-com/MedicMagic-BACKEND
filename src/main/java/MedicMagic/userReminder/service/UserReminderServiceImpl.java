package MedicMagic.userReminder.service;

import MedicMagic.userReminder.dao.UserReminderDao;
import MedicMagic.userReminder.domain.UserReminder;
import MedicMagic.userReminder.dto.UserReminderDto;

import java.util.List;

public class UserReminderServiceImpl implements UserReminderService{
    UserReminderDao userReminderDao;

    public void setUserReminderDao(UserReminderDao userReminderDao) {
        this.userReminderDao = userReminderDao;
    }

    @Override
    public void add(String id) {
        userReminderDao.add(id);
    }

    @Override
    public UserReminder get(String id) {
        return userReminderDao.get(id);
    }

    @Override
    public List<UserReminder> getAll() {
        return userReminderDao.getAll();
    }

    @Override
    public void deleteAll() {
        userReminderDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        userReminderDao.deleteEachId(id);
    }

    @Override
    public void update(UserReminder userReminder) {
        userReminderDao.update(userReminder);
    }

    @Override
    public UserReminderDto conveyUserReminder(String id) {
        return new UserReminderDto(this.get(id));
    }
}
