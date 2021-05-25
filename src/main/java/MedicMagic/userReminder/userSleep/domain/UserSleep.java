package MedicMagic.userReminder.userSleep.domain;

public class UserSleep {
    String id;
    Double sleepGoal;

    public UserSleep() {
    }

    public UserSleep(String id, Double sleepGoal) {
        this.id = id;
        this.sleepGoal = sleepGoal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSleepGoal() {
        return sleepGoal;
    }

    public void setSleepGoal(Double sleepGoal) {
        this.sleepGoal = sleepGoal;
    }
}
