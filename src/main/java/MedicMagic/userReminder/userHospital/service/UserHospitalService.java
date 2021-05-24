package MedicMagic.userReminder.userHospital.service;

import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserHospitalService {
    void add(UserHospitalDto userHospitalDto);
    @Transactional(readOnly = true)
    UserHospitalDto get(String id);
    @Transactional(readOnly = true)
    List<UserHospitalDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachId(String id);
    void update(UserHospitalDto userHospitalDto);
}
