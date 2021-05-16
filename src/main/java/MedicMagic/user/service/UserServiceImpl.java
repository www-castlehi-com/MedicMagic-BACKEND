package MedicMagic.user.service;

import MedicMagic.user.DifferentPasswordException;
import MedicMagic.user.NullKeyException;
import MedicMagic.user.dao.UserDao;
import MedicMagic.user.domain.User;
import MedicMagic.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class UserServiceImpl implements UserService{
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public UserDto signIn(String id, String pw) throws NullPointerException{
        User user = get(id);
        if(id == null || pw == null) {
            throw new NullPointerException("아이디와 패스워드를 입력해주세요");
        }
        else if(user == null) {
            throw new NullKeyException("회원 정보가 없습니다");
        }
        else if(!user.getPassword().equals(pw)) {
            throw new DifferentPasswordException("아이디 혹은 패스워드가 다릅니다");
        }

        return new UserDto(user);
    }
}
