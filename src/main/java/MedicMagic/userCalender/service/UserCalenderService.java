package MedicMagic.userCalender.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserCalenderService {
    void add(UserCalenderDto userCalenderDto);
    @Transactional(readOnly = true)
    UserCalenderDto get(String id, String date);
    @Transactional(readOnly = true)
    List<UserCalenderDto> getAll();
    @Transactional(readOnly = true)
    List<UserCalenderDto> getEachId(String id);
    @Transactional(readOnly = true)
    int getCountEachIdAndDate(String id, String date);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    void update(UserCalenderDto userCalenderDto);
}
