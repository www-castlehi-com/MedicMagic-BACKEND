package MedicMagic.userCalender.service;


import MedicMagic.userCalender.dao.UserCalenderDao;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userPhysiology.dao.UserPhysiologyDao;
import MedicMagic.userPhysiology.domain.UserPhysiology;

import java.util.List;

public class UserCalenderServiceImpl implements UserCalenderService{
    UserCalenderDao userCalenderDao;
    UserPhysiologyDao userPhysiologyDao;

    public void setUserCalenderDao(UserCalenderDao userCalenderDao) {
        this.userCalenderDao = userCalenderDao;
    }

    public void setUserPhysiologyDao(UserPhysiologyDao userPhysiologyDao) {
        this.userPhysiologyDao = userPhysiologyDao;
    }

    @Override
    public void add(UserCalender userCalender) {

        userCalenderDao.add(userCalender);
        if(userCalender.getStartDay() != null) {
            UserPhysiology userPhysiology = new UserPhysiology();
            userPhysiology.setId(userCalender.getId());
            userPhysiology.setStartPhysiology(userCalender.getStartDay());
            userPhysiologyDao.add(userPhysiology);
        }
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
    public int getCountEachIdAndDate(String id, String date) {
        return this.userCalenderDao.getCountEachIdAndDate(id, date);
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
    public void update(UserCalender userCalender) {
        userCalenderDao.update(userCalender);
    }
}
