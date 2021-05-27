package MedicMagic.userCalender.dto;

import MedicMagic.userCalender.domain.UserCalender;

public class UserCalenderDto {
    public String id;
    public String date;
    public String sleepTime;
    public String exerciseTime;
    public Integer waterIntake;
    public String startDay;
    public String endDay;

    public UserCalenderDto(UserCalender userCalender) {
        this.id = userCalender.getId();
        this.date = userCalender.getDate();
        this.sleepTime = userCalender.getSleepTime();
        this.exerciseTime = userCalender.getExerciseTime();
        this.waterIntake = userCalender.getWaterIntake();
        this.startDay = userCalender.getStartDay();
        this.endDay = userCalender.getEndDay();
    }

    public UserCalenderDto(String id, String date, String sleepTime, String exerciseTime, String waterIntake, String startDay, String endDay) {
        this.id = id;
        this.date = date;
        this.sleepTime = sleepTime;
        this.exerciseTime = exerciseTime;
        this.waterIntake = Integer.parseInt(waterIntake);
        if(startDay.equals("null")) {
            this.startDay = null;
        } else {
            this.startDay = startDay;
        }
        if(endDay.equals("null")) {
            this.endDay = null;
        } else {
            this.endDay = endDay;
        }
    }
}
