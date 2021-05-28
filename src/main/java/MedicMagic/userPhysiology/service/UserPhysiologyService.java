package MedicMagic.userPhysiology.service;

import MedicMagic.userPhysiology.domain.UserPhysiology;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserPhysiologyService {
    void add(UserPhysiologyDto userPhysiologyDto);
    List<UserPhysiologyDto> get(String id);
    @Transactional(readOnly = true)
    UserPhysiologyDto getEachIdAndStartPhysiology(String id, String startPhysiology);
    @Transactional(readOnly = true)
    UserPhysiologyDto getLastEachId(String id);
    @Transactional(readOnly = true)
    UserPhysiologyDto getNull(String id);
    @Transactional(readOnly = true)
    List<UserPhysiologyDto> getAll();
    @Transactional(readOnly = true)
    List<UserPhysiologyDto> getEachIdLimit6(String id);
    @Transactional(readOnly = true)
    List<UserPhysiologyDto> getEachIdAndMonth(String id, String month);
    void deleteAll();
    void deleteEachIdAndStartPhysiology(String id, String startPhysiology);
    void deleteEachIdAndEndPhysiology(String id, String endPhysiology);
    @Transactional(readOnly = true)
    int getCountEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachIdAndStartPhysiology(String id, String startPhysiology);
    void update(UserPhysiologyDto userPhysiologyDto);
}
