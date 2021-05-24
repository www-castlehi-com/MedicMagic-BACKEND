package MedicMagic.user.service;

import MedicMagic.exception.DifferentPasswordException;
import MedicMagic.exception.DuplicateUserIdException;
import MedicMagic.exception.NullKeyException;
import MedicMagic.user.dao.UserDao;
import MedicMagic.user.domain.User;
import MedicMagic.user.dto.UserDto;
import MedicMagic.exception.NegativeException;
import MedicMagic.userReminder.userBirthControlPills.dao.UserBirthControlPillsDao;
import MedicMagic.userReminder.userBirthControlPills.dto.UserBirthControlPillsDto;
import MedicMagic.userReminder.userExercise.dao.UserExerciseDao;
import MedicMagic.userReminder.userExercise.dto.UserExerciseDto;
import MedicMagic.userReminder.userHospital.dao.UserHospitalDao;
import MedicMagic.userReminder.userHospital.dto.UserHospitalDto;
import MedicMagic.userReminder.userReminderList.dao.UserReminderListDao;
import MedicMagic.userReminder.userReminderList.dto.UserReminderListDto;
import MedicMagic.userReminder.userReminderPhysiology.dao.UserReminderPhysiologyDao;
import MedicMagic.userReminder.userReminderPhysiology.dto.UserReminderPhysiologyDto;
import MedicMagic.userReminder.userWaterIntake.dao.UserWaterIntakeDao;
import MedicMagic.userReminder.userWaterIntake.dto.UserWaterIntakeDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService{
    UserDao userDao;
    UserReminderListDao userReminderListDao;
    UserBirthControlPillsDao userBirthControlPillsDao;
    UserReminderPhysiologyDao userReminderPhysiologyDao;
    UserHospitalDao userHospitalDao;
    UserWaterIntakeDao userWaterIntakeDao;
    UserExerciseDao userExerciseDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserReminderListDao(UserReminderListDao userReminderListDao) {
        this.userReminderListDao = userReminderListDao;
    }

    public void setUserBirthControlPillsDao(UserBirthControlPillsDao userBirthControlPillsDao) {
        this.userBirthControlPillsDao = userBirthControlPillsDao;
    }

    public void setUserReminderPhysiologyDao(UserReminderPhysiologyDao userReminderPhysiologyDao) {
        this.userReminderPhysiologyDao = userReminderPhysiologyDao;
    }

    public void setUserHospitalDao(UserHospitalDao userHospitalDao) {
        this.userHospitalDao = userHospitalDao;
    }

    public void setUserWaterIntakeDao(UserWaterIntakeDao userWaterIntakeDao) {
        this.userWaterIntakeDao = userWaterIntakeDao;
    }

    public void setUserExerciseDao(UserExerciseDao userExerciseDao) {
        this.userExerciseDao = userExerciseDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
        userReminderListDao.add(new UserReminderListDto(user.getId(), "false", "false", "false", "false", "false", "false"));
        userBirthControlPillsDao.add(new UserBirthControlPillsDto(user.getId(), "08:00:00", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "21"));
        userReminderPhysiologyDao.add(new UserReminderPhysiologyDto(user.getId(), "18:00:00"));
        userHospitalDao.add(new UserHospitalDto(user.getId(), "null", "18:00:00"));
        userWaterIntakeDao.add(new UserWaterIntakeDto(user.getId(), "0.0", "22:00:00"));
        userExerciseDao.add(new UserExerciseDto(user.getId(), "0.0"));
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
