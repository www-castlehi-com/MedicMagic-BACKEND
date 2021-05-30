package MedicMagic.userCalender.service;


import MedicMagic.exception.LastValueNotNullException;
import MedicMagic.exception.LastValueNullException;
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

    public void setUserCalenderDao(UserCalenderDao userCalenderDao) {
        this.userCalenderDao = userCalenderDao;
    }

    public void setUserPhysiologyDao(UserPhysiologyDao userPhysiologyDao) {
        this.userPhysiologyDao = userPhysiologyDao;
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
                if(userPhysiologyDao.getLastEachId(userCalenderDto.id).endPhysiology != userCalenderDto.endDay) {
                    throw new LastValueNotNullException("시작된 주기가 없습니다");
                }
            }
            UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userPhysiologyDao.getLastEachId(userCalenderDto.id).startPhysiology, userCalenderDto.endDay, "null", "null");
            userPhysiologyDao.update(userPhysiologyDto);
        }
    }

    @Override
    public UserCalenderDto get(String id, String date) {
        if(userCalenderDao.getCountEachIdAndDate(id, date) == 0) {
            this.add(new UserCalenderDto(id, date, "00:00:00", "00:00:00", "0", "null", "null"));
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
            System.out.println("11");
            if(userPhysiologyDao.getCountEachIdAndStartPhysiology(userCalenderDto.id, userCalenderDto.startDay) == 0) {
                System.out.println("22");
                if(userPhysiologyDao.getNullCount(userCalenderDto.id) != 0) {
                    if(userPhysiologyDao.getNull(userCalenderDto.id).endPhysiology == null) {
                        throw new LastValueNullException("이전 주기를 완료해주세요");
                    } else {
                        UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userCalenderDto.startDay, "null", "null", "null");
                        userPhysiologyDao.add(userPhysiologyDto);
                    }
                } else {
                    UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userCalenderDto.startDay, "null", "null", "null");
                    userPhysiologyDao.add(userPhysiologyDto);
                }
            } else {
                UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userCalenderDto.startDay, "null", "null", "null");
                userPhysiologyDao.update(userPhysiologyDto);
            }
        } else {
            if(userPhysiologyDao.getCountEachIdAndStartPhysiology(userCalenderDto.id, userCalenderDto.date) == 1) {
                String endDay = userPhysiologyDao.getEachIdAndStartPhysiology(userCalenderDto.id, userCalenderDto.date).endPhysiology;
                userPhysiologyDao.deleteEachIdAndStartPhysiology(userCalenderDto.id, userCalenderDto.date);
                UserCalenderDto userCalenderDto1 = userCalenderDao.get(userCalenderDto.id, endDay);
                userCalenderDto1.endDay = null;
                userCalenderDao.update(userCalenderDto1);
            }
        }
        if(userCalenderDto.endDay != null) {
            if(userPhysiologyDao.getCountEachId(userCalenderDto.id) != 0) {
                if(userPhysiologyDao.getLastEachId(userCalenderDto.id).endPhysiology != null && userPhysiologyDao.getNull(userCalenderDto.id).endPhysiology != null) {
                    if(!userPhysiologyDao.getLastEachId(userCalenderDto.id).endPhysiology.equals(userCalenderDto.endDay)) {
                        throw new LastValueNotNullException("시작된 주기가 없습니다");
                    }
                } else {
                    String startDay;
                    boolean getLastEachId = true;
                    if(userPhysiologyDao.getLastEachId(userCalenderDto.id).endPhysiology == null) {
                        startDay = userPhysiologyDao.getLastEachId(userCalenderDto.id).startPhysiology;
                        getLastEachId = true;
                    } else {
                        startDay = userPhysiologyDao.getNull(userCalenderDto.id).startPhysiology;
                        getLastEachId = false;
                    }

                    if(startDay.compareTo(userCalenderDto.endDay) < 0) {
                        UserPhysiologyDto userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userPhysiologyDao.getLastEachId(userCalenderDto.id).startPhysiology, userCalenderDto.endDay, "null", "null");;
                        if(getLastEachId == false) {
                            userPhysiologyDto = new UserPhysiologyDto(userCalenderDto.id, userPhysiologyDao.getNull(userCalenderDto.id).startPhysiology, userCalenderDto.endDay, "null", "null");
                        }
                        userPhysiologyDao.update(userPhysiologyDto);
                    } else {
                        throw new IllegalArgumentException("시작 날짜를 끝나는 날짜 이전으로 맞춰주세요");
                    }
                }
            } else {
                throw new LastValueNotNullException("시작된 주기가 없습니다");
            }
        } else {
            if(userPhysiologyDao.getCountEachIdAndEndPhysiology(userCalenderDto.id, userCalenderDto.date) == 1) {
                UserPhysiologyDto userPhysiologyDto = userPhysiologyDao.getEachIdAndEndPhysiology(userCalenderDto.id, userCalenderDto.date);
                userPhysiologyDto.endPhysiology = null;
                userPhysiologyDao.update(userPhysiologyDto);
            }
        }
        userCalenderDao.update(userCalenderDto);
    }
}
