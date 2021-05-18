package MedicMagic.user.service;

import MedicMagic.user.DifferentPasswordException;
import MedicMagic.user.dao.UserDao;
import MedicMagic.user.domain.User;
import MedicMagic.user.dto.UserDto;
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
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public UserDto signIn(String id, String pw){
        User user = get(id);
        if(!user.getPassword().equals(pw)) {
            throw new DifferentPasswordException("아이디 혹은 패스워드가 다릅니다");
        }

        return new UserDto(user);
    }
}
