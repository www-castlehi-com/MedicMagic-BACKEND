package MedicMagic.userCalender.service;


import MedicMagic.userCalender.dao.UserCalenderDao;
import MedicMagic.userCalender.domain.UserCalender;

import java.util.List;

public class UserCalenderServiceImpl implements UserCalenderService{
    UserCalenderDao userCalenderDao;

    public void setUserCalenderDao(UserCalenderDao userCalenderDao) {
        this.userCalenderDao = userCalenderDao;
    }

    @Override
    public void add(UserCalender userCalender) {
        userCalenderDao.add(userCalender);
    }

    @Override
    public UserCalender get(String id, String date) {
        return userCalenderDao.get(id, date);
    }

    @Override
    public List<UserCalender> getAll() {
        return userCalenderDao.getAll();
    }

    @Override
    public List<UserCalender> getEachId(String id) {
        return userCalenderDao.getEachId(id);
    }

    @Override
    public void deleteAll() {
        userCalenderDao.deleteAll();
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        userCalenderDao.deleteEachIdAndDate(id, date);
    }

    @Override
    public void update(UserCalender userCalender, String column, Object object) {
        userCalenderDao.update(userCalender, column, object);
    }
}
