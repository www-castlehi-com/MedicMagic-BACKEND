package MedicMagic.userReminder.userHospital.dao;

import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;

import java.util.List;

public interface UserHospitalDao {
    void add(UserHospitalDto userHospitalDto);
    UserHospitalDto get(String id);
    List<UserHospitalDto> getAll();
    void deleteAll();
    void deleteEachId(String id);
    int getCount();
    int getCountEachId(String id);
    void update(UserHospitalDto userHospitalDto);
}
