package MedicMagic.userReminder.userReminderPhysiology.service;

import MedicMagic.userReminder.userReminderPhysiology.dao.UserReminderPhysiologyDao;
import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;

import java.util.List;

public class UserReminderPhysiologyServiceImpl implements UserReminderPhysiologyService{
    private UserReminderPhysiologyDao userReminderPhysiologyDao;

    public void setUserReminderPhysiologyDao(UserReminderPhysiologyDao userReminderPhysiologyDao) {
        this.userReminderPhysiologyDao = userReminderPhysiologyDao;
    }

    @Override
    public void add(UserReminderPhysiologyDto userPhysiologyDto) {
       this.userReminderPhysiologyDao.add(userPhysiologyDto);
    }

    @Override
    public UserReminderPhysiologyDto get(String id) {
        return this.userReminderPhysiologyDao.get(id);
    }

    @Override
    public List<UserReminderPhysiologyDto> getAll() {
        return this.userReminderPhysiologyDao.getAll();
    }

    @Override
    public void deleteAll() {
        this.userReminderPhysiologyDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        this.userReminderPhysiologyDao.deleteEachId(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userReminderPhysiologyDao.getCountEachId(id);
    }

    @Override
    public void update(UserReminderPhysiologyDto userPhysiologyDto) {
        this.userReminderPhysiologyDao.update(userPhysiologyDto);
    }
}
