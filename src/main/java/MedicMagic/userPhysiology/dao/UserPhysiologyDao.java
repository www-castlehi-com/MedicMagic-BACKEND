package MedicMagic.userPhysiology.dao;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userPhysiology.domain.UserPhysiology;

import java.util.List;

public interface UserPhysiologyDao {
    void add(UserPhysiology userPhysiology);
    List<UserPhysiology> get(String id);
    UserPhysiology getEachIdAndStartPhysiology(String id, String startPhysiology);
    UserPhysiology getLastEachId(String id);
    List<UserPhysiology> getAll();
    List<UserPhysiology> getEachIdLimit3(String id);
    void deleteAll();
    void deleteEachIdAndStartPhysiology(String id, String startPhysiology);
    void deleteEachIdAndEndPhysiology(String id, String endPhysiology);
    int getCount();
    int getCountEachId(String id);
    void update(UserPhysiology userPhysiology);
}
