package MedicMagic.userCalender.service;


import MedicMagic.exception.LastValueNotNullException;
import MedicMagic.userCalender.dao.UserCalenderDao;
import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userCalender.dto.UserCalenderDto;
import MedicMagic.userMucus.dao.UserMucusDao;
import MedicMagic.userMucus.dto.UserMucusDto;
import MedicMagic.userPhysiology.dao.UserPhysiologyDao;
import MedicMagic.userPhysiology.domain.UserPhysiology;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;
import MedicMagic.userSymptom.dao.UserSymptomDao;
import MedicMagic.userSymptom.dto.UserSymptomDto;

import java.util.List;

public class UserCalenderServiceImpl implements UserCalenderService{
    UserCalenderDao userCalenderDao;
    UserPhysiologyDao userPhysiologyDao;
    UserSymptomDao userSymptomDao;
    UserMucusDao userMucusDao;

    public void setUserCalenderDao(UserCalenderDao userCalenderDao) {
        this.userCalenderDao = userCalenderDao;
    }

    public void setUserPhysiologyDao(UserPhysiologyDao userPhysiologyDao) {
        this.userPhysiologyDao = userPhysiologyDao;
    }

    public void setUserSymptomDao(UserSymptomDao userSymptomDao) {
        this.userSymptomDao = userSymptomDao;
    }

    public void setUserMucusDao(UserMucusDao userMucusDao) {
        this.userMucusDao = userMucusDao;
    }

    @Override
    public void add(UserCalenderDto userCalenderDto) {

        userCalenderDao.add(userCalenderDto);
        if(userCalenderDto.startDay != null) {
            UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userCalenderDto.startDay, "null", "null", "null");
            userPhysiologyDao.add(userPhysiologyDto);
        }
        if(userCalenderDto.endDay != null) {
            if(userPhysiologyDao.getLastEachId(userCalenderDto.id).endPhysiology != null) {
                throw new LastValueNotNullException("시작된 주기가 없습니다");
            }
            UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userPhysiologyDao.getLastEachId(userCalenderDto.id).startPhysiology, userCalenderDto.endDay, "null", "null");
            userPhysiologyDao.update(userPhysiologyDto);
        }
        if(userCalenderDto.symptom == true) {
            UserSymptomDto userSymptomDto = new UserSymptomDto(userCalenderDto.id, userCalenderDto.date, "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false");
            userSymptomDao.add(userSymptomDto);
        } else {
            userSymptomDao.deleteEachIdAndDate(userCalenderDto.id, userCalenderDto.date);
        }
        if(userCalenderDto.mucus == true) {
            UserMucusDto userMucusDto = new UserMucusDto(userCalenderDto.id, userCalenderDto.date, "false", "false", "false", "false", "false", "false", "false");
            userMucusDao.add(userMucusDto);
        } else {
            userMucusDao.deleteEachIdAndDate(userCalenderDto.id, userCalenderDto.date);
        }
    }

    @Override
    public UserCalenderDto get(String id, String date) {
        if(userCalenderDao.getCountEachIdAndDate(id, date) == 0) {
            this.add(new UserCalenderDto(id, date, "00:00:00", "00:00:00", "0", "null", "null", "false", "false"));
        }
        return userCalenderDao.get(id, date);
    }

    @Override
    public List<UserCalenderDto> getAll() {
        return userCalenderDao.getAll();
    }

    @Override
    public List<UserCalenderDto> getEachId(String id) {
        return userCalenderDao.getEachId(id);
    }

    @Override
    public int getCountEachIdAndDate(String id, String date) {
        return this.userCalenderDao.getCountEachIdAndDate(id, date);
    }

    @Override
    public void deleteAll() {
        userCalenderDao.deleteAll();
    }

    @Override
    public void deleteEachIdAndDate(String id, String date) {
        userCalenderDao.deleteEachIdAndDate(id, date);
    }

    @Override
    public void update(UserCalenderDto userCalenderDto) {
        if(userCalenderDto.startDay != null) {
            if(userPhysiologyDao.getCountEachIdAndStartPhysiology(userCalenderDto.id, userCalenderDto.startDay) == 0) {
                UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userCalenderDto.startDay, "null", "null", "null");
                userPhysiologyDao.add(userPhysiologyDto);
            } else {
                UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userCalenderDto.startDay, "null", "null", "null");
                userPhysiologyDao.update(userPhysiologyDto);
            }
        }
        if(userCalenderDto.endDay != null) {
            if(userPhysiologyDao.getLastEachId(userCalenderDto.id).endPhysiology != null) {
                throw new LastValueNotNullException("시작된 주기가 없습니다");
            }
            if(!userPhysiologyDao.getLastEachId(userCalenderDto.id).endPhysiology.equals(userCalenderDto.endDay)) {
                UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userPhysiologyDao.getLastEachId(userCalenderDto.id).startPhysiology, userCalenderDto.endDay, "null", "null");
                userPhysiologyDao.update(userPhysiologyDto);
            }
        }
        if(userCalenderDto.symptom == true) {
            if(userSymptomDao.getCountEachIdAndDate(userCalenderDto.id, userCalenderDto.date) == 0) {
                UserSymptomDto userSymptomDto = new UserSymptomDto(userCalenderDto.id, userCalenderDto.date, "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false", "false");
                userSymptomDao.add(userSymptomDto);
            }
        } else {
            if(userSymptomDao.getCountEachIdAndDate(userCalenderDto.id, userCalenderDto.date) != 0) {
                userSymptomDao.deleteEachIdAndDate(userCalenderDto.id, userCalenderDto.date);
            }
        }
        if(userCalenderDto.mucus == true) {
            if(userMucusDao.getCountEachIdAndDate(userCalenderDto.id, userCalenderDto.date) == 0) {
                UserMucusDto userMucusDto = new UserMucusDto(userCalenderDto.id, userCalenderDto.date, "false", "false", "false", "false", "false", "false", "false");
                userMucusDao.add(userMucusDto);
            }
        } else {
            if(userMucusDao.getCountEachIdAndDate(userCalenderDto.id, userCalenderDto.date) != 0) {
                userMucusDao.deleteEachIdAndDate(userCalenderDto.id, userCalenderDto.date);
            }
        }
        userCalenderDao.update(userCalenderDto);
    }
}
