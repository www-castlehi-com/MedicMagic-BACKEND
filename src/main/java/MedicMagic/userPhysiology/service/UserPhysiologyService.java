package MedicMagic.userPhysiology.service;

import MedicMagic.userPhysiology.domain.UserPhysiology;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserPhysiologyService {
    void add(UserPhysiology userPhysiology);
    @Transactional(readOnly = true)
    List<UserPhysiology> get(String id);
    @Transactional(readOnly = true)
    UserPhysiology getEachIdAndStartPhysiology(String id, String startPhysiology);
    @Transactional(readOnly = true)
    UserPhysiology getLastEachId(String id);
    @Transactional(readOnly = true)
    List<UserPhysiology> getAll();
    @Transactional(readOnly = true)
    List<UserPhysiology> getEachIdLimit3(String id);
    void deleteAll();
    void deleteEachIdAndStartPhysiology(String id, String startPhysiology);
    void deleteEachIdAndEndPhysiology(String id, String endPhysiology);
    void update(UserPhysiology userPhysiology);

}
