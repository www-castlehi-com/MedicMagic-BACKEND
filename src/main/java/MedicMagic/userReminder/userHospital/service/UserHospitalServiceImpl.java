package MedicMagic.userReminder.userHospital.service;

import MedicMagic.userReminder.userHospital.dao.UserHospitalDao;
import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;

import java.util.List;

public class UserHospitalServiceImpl implements UserHospitalService{
    private UserHospitalDao userHospitalDao;

    public void setUserHospitalDao(UserHospitalDao userHospitalDao) {
        this.userHospitalDao = userHospitalDao;
    }

    @Override
    public void add(UserHospitalDto userHospitalDto) {
        this.userHospitalDao.add(userHospitalDto);
    }

    @Override
    public UserHospitalDto get(String id) {
        return this.userHospitalDao.get(id);
    }

    @Override
    public List<UserHospitalDto> getAll() {
        return this.userHospitalDao.getAll();
    }

    @Override
    public void deleteAll() {
        this.userHospitalDao.deleteAll();
    }

    @Override
    public void deleteEachId(String id) {
        this.userHospitalDao.deleteEachId(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userHospitalDao.getCountEachId(id);
    }

    @Override
    public void update(UserHospitalDto userHospitalDto) {
        this.userHospitalDao.update(userHospitalDto);
    }
}
