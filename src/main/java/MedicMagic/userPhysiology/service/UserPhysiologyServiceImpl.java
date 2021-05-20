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
    public void add(UserPhysiology userPhysiology) {
        this.userPhysiologyDao.add(userPhysiology);
    }

    @Override
    public List<UserPhysiology> get(String id) {
        return this.userPhysiologyDao.get(id);
    }

    @Override
    public UserPhysiology getEachIdAndStartPhysiology(String id, String startPhysiology) {
        return this.userPhysiologyDao.getEachIdAndStartPhysiology(id, startPhysiology);
    }

    @Override
    public UserPhysiology getLastEachId(String id) {
        return this.userPhysiologyDao.getLastEachId(id);
    }

    @Override
    public List<UserPhysiology> getAll() {
        return this.userPhysiologyDao.getAll();
    }

    @Override
    public List<UserPhysiology> getEachIdLimit3(String id) {
        return this.userPhysiologyDao.getEachIdLimit3(id);
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
    public void update(UserPhysiology userPhysiology) {
        this.userPhysiologyDao.update(userPhysiology);
    }

    @Override
    public UserPhysiologyDto getLastEachIdTransform(String id) {
        return new UserPhysiologyDto(this.getLastEachId(id));
    }

    @Override
    public List<UserPhysiologyDto> getEachIdLimit3Transform(String id) {
        List<UserPhysiologyDto> userPhysiologyDtoList = new ArrayList<>();
        for(UserPhysiology userPhysiology : this.getEachIdLimit3(id)) {
            userPhysiologyDtoList.add(new UserPhysiologyDto(userPhysiology));
        }
        return userPhysiologyDtoList;
    }
}
