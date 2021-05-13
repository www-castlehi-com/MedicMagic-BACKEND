package MedicMagic.userGoal.domain;

public class UserGoal {
    String id;
    Double weigh;
    String sleepTime;
    String exerciseTime;
    Double waterIntake;

    public UserGoal() {
    }

    public UserGoal(String id, Double weigh, String sleepTime, String exerciseTime, Double waterIntake) {
        this.id = id;
        this.weigh = weigh;
        this.sleepTime = sleepTime;
        this.exerciseTime = exerciseTime;
        this.waterIntake = waterIntake;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
