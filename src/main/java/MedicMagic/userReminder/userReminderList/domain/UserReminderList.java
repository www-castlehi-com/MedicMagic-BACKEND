package MedicMagic.userReminder.userReminderList.domain;

public class UserReminderList {
    String id;
    boolean birthControlPills;
    boolean physiology;
    boolean hospital;
    boolean waterIntake;
    boolean exerciseTimeGoal;
    boolean sleepTimeGoal;

    public UserReminderList() {
    }

    public UserReminderList(String id, boolean birthControlPills, boolean physiology, boolean hospital, boolean waterIntake, boolean exerciseTimeGoal, boolean sleepTimeGoal) {
        this.id = id;
        this.birthControlPills = birthControlPills;
        this.physiology = physiology;
        this.hospital = hospital;
        this.waterIntake = waterIntake;
        this.exerciseTimeGoal = exerciseTimeGoal;
        this.sleepTimeGoal = sleepTimeGoal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBirthControlPills() {
        return birthControlPills;
    }

    public void setBirthControlPills(boolean birthControlPills) {
        this.birthControlPills = birthControlPills;
    }

    public boolean isPhysiology() {
        return physiology;
    }

    public void setPhysiology(boolean physiology) {
        this.physiology = physiology;
    }

    public boolean isHospital() {
        return hospital;
    }

    public void setHospital(boolean hospital) {
        this.hospital = hospital;
    }

    public boolean isWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(boolean waterIntake) {
        this.waterIntake = waterIntake;
    }

    public boolean isExerciseTimeGoal() {
        return exerciseTimeGoal;
    }

    public void setExerciseTimeGoal(boolean exerciseTimeGoal) {
        this.exerciseTimeGoal = exerciseTimeGoal;
    }

    public boolean isSleepTimeGoal() {
        return sleepTimeGoal;
    }

    public void setSleepTimeGoal(boolean sleepTimeGoal) {
        this.sleepTimeGoal = sleepTimeGoal;
    }
}
