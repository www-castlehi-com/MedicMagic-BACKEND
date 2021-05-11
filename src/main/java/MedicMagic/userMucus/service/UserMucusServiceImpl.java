package MedicMagic.userMucus.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.dao.UserMucusDao;
import MedicMagic.userMucus.domain.UserMucus;

import java.util.List;

public class UserMucusServiceImpl implements UserMucusService{
    UserMucusDao userMucusDao;

    public void setUserMucusDao(UserMucusDao userMucusDao) {
        this.userMucusDao = userMucusDao;
    }

    @Override
    public void add(UserMucus userMucus) {
        userMucusDao.add(userMucus);
    }

    @Override
    public UserMucus get(String id, String date) {
        return userMucusDao.get(id, date);
    }

    @Override
    public List<UserMucus> getAll() {
        return userMucusDao.getAll();
    }

    @Override
    public List<UserMucus> getEachId(String id) {
        return userMucusDao.getEachId(id);
    }

    @Override
    public List<String> getMucusTrue(UserMucus userMucus) {
        return userMucusDao.getMucusTrue(userMucus);
    }

    @Override
    public void deleteAll() {
        userMucusDao.deleteAll();
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        userMucusDao.deleteEachIdAndDate(id, date);
    }

    @Override
    public void update(UserMucus userMucus, String column, boolean object) {
        userMucusDao.update(userMucus, column, object);
    }

    @Override
    public void updateUserCalenderIfMucusIsFalse(UserMucus userMucus, UserCalender userCalender) {
        userMucusDao.updateUserCalenderIfMucusIsFalse(userMucus, userCalender);
    }
}
