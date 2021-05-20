package MedicMagic.userCalender.dto;

import MedicMagic.userCalender.domain.UserCalender;

public class UserCalenderDto {
    public String id;
    public String date;
    public Double weigh;
    public String sleepTime;
    public String exerciseTime;
    public Double waterIntake;
    public String startDay;
    public String endDay;
    public String emotion;
    public boolean symptom;
    public boolean mucus;

    public UserCalenderDto(UserCalender userCalender) {
        this.id = userCalender.getId();
        this.date = userCalender.getDate();
        this.weigh = userCalender.getWeigh();
        this.sleepTime = userCalender.getSleepTime();
        this.exerciseTime = userCalender.getExerciseTime();
        this.waterIntake = userCalender.getWaterIntake();
        this.startDay = userCalender.getStartDay();
        this.endDay = userCalender.getEndDay();
        this.emotion = userCalender.getEmotion();
        this.symptom = userCalender.isSymptom();
        this.mucus = userCalender.isMucus();
    }
}
