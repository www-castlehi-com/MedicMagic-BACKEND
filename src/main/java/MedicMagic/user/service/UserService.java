package MedicMagic.user.service;

import MedicMagic.user.domain.User;
import MedicMagic.user.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    void add(User user);
    @Transactional(readOnly = true)
    User get(String id);
    @Transactional(readOnly = true)
    List<User> getAll();
    int getCountEachId(String id);
    void deleteAll();
    void update(User user);

    UserDto signIn(String id, String pw);
    void signUp(UserDto userDto);
}
