package MedicMagic.userCalender.service;

import MedicMagic.userCalender.domain.UserCalender;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserCalenderService {
    void add(UserCalender userCalender);
    @Transactional(readOnly = true)
    UserCalender get(String id, String date);
    @Transactional(readOnly = true)
    List<UserCalender> getAll();
    @Transactional(readOnly = true)
    List<UserCalender> getEachId(String id);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    void update(UserCalender userCalender);
}
