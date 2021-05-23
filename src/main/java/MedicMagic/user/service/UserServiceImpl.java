package MedicMagic.user.service;

import MedicMagic.user.DifferentPasswordException;
import MedicMagic.user.DuplicateUserIdException;
import MedicMagic.user.NullKeyException;
import MedicMagic.user.dao.UserDao;
import MedicMagic.user.domain.User;
import MedicMagic.user.dto.UserDto;
import MedicMagic.userCalender.NegativeException;
import MedicMagic.userGoal.dao.UserGoalDao;
import MedicMagic.userReminder.dao.UserReminderDao;

import java.util.List;

public class UserServiceImpl implements UserService{
    UserDao userDao;
    UserGoalDao userGoalDao;
    UserReminderDao userReminderDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserGoalDao(UserGoalDao userGoalDao) {
        this.userGoalDao = userGoalDao;
    }

    public void setUserReminderDao(UserReminderDao userReminderDao) {
        this.userReminderDao = userReminderDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
        userGoalDao.add(user.getId());
        userReminderDao.add(user.getId());
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
    public int getCountEachId(String id) {
        return userDao.getCountEachId(id);
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
    public UserDto signIn(String id, String pw){

        if(id.equals("null") || pw.equals("null")) {
            throw new NullKeyException("필수 입력 사항입니다");
        }

        User user = get(id);
        if(!user.getPassword().equals(pw)) {
            throw new DifferentPasswordException("아이디 혹은 패스워드가 다릅니다");
        }

        return new UserDto(user);
    }

    @Override
    public void signUp(UserDto userDto) {
        if(userDto.id == null || userDto.name == null || userDto.password == null || userDto.birthday == null || userDto.age == null) {
            throw new NullKeyException("필수 입력 사항입니다");
        }

        if(userDto.age < 0) {
            throw new NegativeException("양수를 입력해주세요");
        }

        if(this.getCountEachId(userDto.id) == 0) {
            this.add(new User(userDto.id, userDto.name, userDto.password, userDto.birthday, userDto.age));
        } else {
            throw new DuplicateUserIdException("이미 가입된 아이디입니다");
        }
    }
}
