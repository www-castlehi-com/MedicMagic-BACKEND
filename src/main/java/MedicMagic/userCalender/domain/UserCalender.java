package MedicMagic.userCalender.domain;


public class UserCalender {
    String id;
    String date;
    String sleepTime;
    String exerciseTime;
    Integer waterIntake;
    String startDay;
    String endDay;

    public UserCalender(String id, String date, String sleepTime, String exerciseTime, Integer waterIntake, String startDay, String endDay) {
        this.id = id;
        this.date = date;
        this.sleepTime = sleepTime;
        this.exerciseTime = exerciseTime;
        this.waterIntake = waterIntake;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public UserCalender() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Integer getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Integer waterIntake) {
        this.waterIntake = waterIntake;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }
}
