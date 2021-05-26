package MedicMagic.userMucus.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.dao.UserMucusDao;
import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userMucus.dto.UserMucusDto;

import java.util.List;

public class UserMucusServiceImpl implements UserMucusService{
    UserMucusDao userMucusDao;

    public void setUserMucusDao(UserMucusDao userMucusDao) {
        this.userMucusDao = userMucusDao;
    }

    @Override
    public void add(UserMucusDto userMucusDto) {
        userMucusDao.add(userMucusDto);
    }

    @Override
    public UserMucusDto get(String id, String date) {
        return userMucusDao.get(id, date);
    }

    @Override
    public List<UserMucusDto> getAll() {
        return userMucusDao.getAll();
    }

    @Override
    public List<UserMucusDto> getEachId(String id) {
        return userMucusDao.getEachId(id);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return userMucusDao.getCountEachIdAndDate(id, date);
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
    public void update(UserMucusDto userMucusDto) {
        userMucusDao.update(userMucusDto);
    }
}
