package MedicMagic.userSymptom.service;

import MedicMagic.userCalender.dao.UserCalenderDao;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.dao.UserSymptomDao;
import MedicMagic.userSymptom.domain.UserSymptom;
import MedicMagic.userSymptom.dto.UserSymptomDto;

import java.util.List;

public class UserSymptomServiceImpl implements UserSymptomService {
    UserSymptomDao userSymptomDao;

    public void setUserSymptomDao(UserSymptomDao userSymptomDao) {
        this.userSymptomDao = userSymptomDao;
    }

    @Override
    public void add(UserSymptomDto userSymptomDto) {
        userSymptomDao.add(userSymptomDto);
    }

    @Override
    public UserSymptomDto get(String id, String date) {
        return userSymptomDao.get(id, date);
    }

    @Override
    public List<UserSymptomDto> getAll() {
        return userSymptomDao.getAll();
    }

    @Override
    public List<UserSymptomDto> getEachId(String id) {
        return userSymptomDao.getEachId(id);
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
    public void update(UserSymptomDto userSymptomDto) {
        userSymptomDao.update(userSymptomDto);
    }
}
