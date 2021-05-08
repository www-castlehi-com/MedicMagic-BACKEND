package MedicMagic.userCalender.domain;


public class UserCalender {
    String id;
    String date;
    Double weigh;
    String sleepTime;
    String exerciseTime;
    Double waterIntake;
    String startDay;
    String endDay;
    String emotion;
    boolean symptom;
    boolean mucus;

    public UserCalender(String id, String date, Double weigh, String sleepTime, String exerciseTime, Double waterIntake, String startDay, String endDay, String emotion, boolean symptom, boolean mucus) {
        this.id = id;
        this.date = date;
        this.weigh = weigh;
        this.sleepTime = sleepTime;
        this.exerciseTime = exerciseTime;
        this.waterIntake = waterIntake;
        this.startDay = startDay;
        this.endDay = endDay;
        this.emotion = emotion;
        this.symptom = symptom;
        this.mucus = mucus;
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

    public Double getWeigh() {
        return weigh;
    }

    public void setWeigh(Double weigh) {
        this.weigh = weigh;
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

    public Double getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Double waterIntake) {
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

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public boolean isSymptom() {
        return symptom;
    }

    public void setSymptom(boolean symptom) {
        this.symptom = symptom;
    }

    public boolean isMucus() {
        return mucus;
    }

    public void setMucus(boolean mucus) {
        this.mucus = mucus;
    }
}
