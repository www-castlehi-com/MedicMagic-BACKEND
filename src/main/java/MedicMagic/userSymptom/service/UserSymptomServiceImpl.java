package MedicMagic.userSymptom.service;

import MedicMagic.userCalender.dao.UserCalenderDao;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.dao.UserSymptomDao;
import MedicMagic.userSymptom.domain.UserSymptom;

import java.util.List;

public class UserSymptomServiceImpl implements UserSymptomService {
    UserSymptomDao userSymptomDao;

    public void setUserSymptomDao(UserSymptomDao userSymptomDao) {
        this.userSymptomDao = userSymptomDao;
    }

    @Override
    public void add(UserSymptom userSymptom) {
        userSymptomDao.add(userSymptom);
    }

    @Override
    public UserSymptom get(String id, String date) {
        return userSymptomDao.get(id, date);
    }

    @Override
    public List<UserSymptom> getAll() {
        return userSymptomDao.getAll();
    }

    @Override
    public List<UserSymptom> getEachId(String id) {
        return userSymptomDao.getEachId(id);
    }

    @Override
    public List<String> getSymptomTrue(UserSymptom userSymptom) {
        return userSymptomDao.getSymptomTrue(userSymptom);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return userSymptomDao.getCountEachIdAndDate(id, date);
    }

    @Override
    public void deleteAll() {
        userSymptomDao.deleteAll();
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        userSymptomDao.deleteEachIdAndDate(id, date);
    }

    @Override
    public void update(UserSymptom userSymptom) {
        userSymptomDao.update(userSymptom);
    }

    @Override
    public void updateUserCalenderIfSymptomIsFalse(UserSymptom userSymptom, UserCalender userCalender) {
        userSymptomDao.updateUserCalenderIfSymptomIsFalse(userSymptom, userCalender);
    }
}
