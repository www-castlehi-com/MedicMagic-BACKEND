package MedicMagic.userMucus.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userMucus.domain.UserMucus;
import MedicMagic.userMucus.dto.UserMucusDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserMucusService {
    void add(UserMucusDto userMucusDto);
    @Transactional(readOnly = true)
    UserMucusDto get(String id, String date);
    @Transactional(readOnly = true)
    List<UserMucusDto> getAll();
    @Transactional(readOnly = true)
    List<UserMucusDto> getEachId(String id);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    @Transactional(readOnly = true)
    int getCountEachIdAndDate(String id, String date);
    void update(UserMucusDto userMucusDto);
}
