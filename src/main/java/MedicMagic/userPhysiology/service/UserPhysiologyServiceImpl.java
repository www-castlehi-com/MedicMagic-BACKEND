package MedicMagic.userPhysiology.service;

import MedicMagic.userPhysiology.dao.UserPhysiologyDao;
import MedicMagic.userPhysiology.domain.UserPhysiology;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserPhysiologyServiceImpl implements UserPhysiologyService{
    UserPhysiologyDao userPhysiologyDao;

    public void setUserPhysiologyDao(UserPhysiologyDao userPhysiologyDao) {
        this.userPhysiologyDao = userPhysiologyDao;
    }

    @Override
    public void add(UserPhysiologyDto userPhysiologyDto) {
        this.userPhysiologyDao.add(userPhysiologyDto);
    }

    @Override
    public List<UserPhysiologyDto> get(String id) {
        return this.userPhysiologyDao.get(id);
    }

    @Override
    public UserPhysiologyDto getEachIdAndStartPhysiology(String id, String startPhysiology) {
        return this.userPhysiologyDao.getEachIdAndStartPhysiology(id, startPhysiology);
    }

    @Override
    public UserPhysiologyDto getLastEachId(String id) {
        return this.userPhysiologyDao.getLastEachId(id);
    }

    @Override
    public List<UserPhysiologyDto> getAll() {
        return this.userPhysiologyDao.getAll();
    }

    @Override
    public List<UserPhysiologyDto> getEachIdLimit6(String id) {
        return this.userPhysiologyDao.getEachIdLimit6(id);
    }

    @Override
    public List<UserPhysiologyDto> getEachIdAndMonth(String id, String month) {
        return this.userPhysiologyDao.getEachIdAndMonth(id, month);
    }

    @Override
    public UserPhysiologyDto getNull(String id) {
        return this.userPhysiologyDao.getNull(id);
    }

    @Override
    public int getCountEachId(String id) {
        return this.userPhysiologyDao.getCountEachId(id);
    }

    @Override
    public int getCountEachIdAndStartPhysiology(String id, String startPhysiology) {
        return this.userPhysiologyDao.getCountEachIdAndStartPhysiology(id, startPhysiology);
    }

    @Override
    public void deleteAll() {
        this.userPhysiologyDao.deleteAll();
    }

    @Override
    public void deleteEachIdAndStartPhysiology(String id, String startPhysiology) {
        this.userPhysiologyDao.deleteEachIdAndStartPhysiology(id, startPhysiology);
    }

    @Override
    public void deleteEachIdAndEndPhysiology(String id, String endPhysiology) {
        this.userPhysiologyDao.deleteEachIdAndEndPhysiology(id, endPhysiology);
    }

    @Override
    public void update(UserPhysiologyDto userPhysiologyDto) {
        this.userPhysiologyDao.update(userPhysiologyDto);
    }
}
