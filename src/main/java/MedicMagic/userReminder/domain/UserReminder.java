package MedicMagic.userReminder.domain;

public class UserReminder {
    String id;
    boolean birthControlPills;
    Integer beforeBirthControlPills;
    String birthControlPillsTime;
    boolean physiology;
    Integer beforePhysiology;
    boolean sleepTimeGoal;
    boolean exerciseTimeGoal;
    boolean hospital;
    Integer hospitalDate;

    public UserReminder(String id, boolean birthControlPills, Integer beforeBirthControlPills, String birthControlPillsTime, boolean physiology, Integer beforePhysiology, boolean sleepTimeGoal, boolean exerciseTimeGoal, boolean hospital, Integer hospitalDate) {
        this.id = id;
        this.birthControlPills = birthControlPills;
        this.beforeBirthControlPills = beforeBirthControlPills;
        this.birthControlPillsTime = birthControlPillsTime;
        this.physiology = physiology;
        this.beforePhysiology = beforePhysiology;
        this.sleepTimeGoal = sleepTimeGoal;
        this.exerciseTimeGoal = exerciseTimeGoal;
        this.hospital = hospital;
        this.hospitalDate = hospitalDate;
    }

    public UserReminder() {
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

    public Integer getBeforeBirthControlPills() {
        return beforeBirthControlPills;
    }

    public void setBeforeBirthControlPills(Integer beforeBirthControlPills) {
        this.beforeBirthControlPills = beforeBirthControlPills;
    }

    public String getBirthControlPillsTime() {
        return birthControlPillsTime;
    }

    public void setBirthControlPillsTime(String birthControlPillsTime) {
        this.birthControlPillsTime = birthControlPillsTime;
    }

    public boolean isPhysiology() {
        return physiology;
    }

    public void setPhysiology(boolean physiology) {
        this.physiology = physiology;
    }

    public Integer getBeforePhysiology() {
        return beforePhysiology;
    }

    public void setBeforePhysiology(Integer beforePhysiology) {
        this.beforePhysiology = beforePhysiology;
    }

    public boolean isSleepTimeGoal() {
        return sleepTimeGoal;
    }

    public void setSleepTimeGoal(boolean sleepTimeGoal) {
        this.sleepTimeGoal = sleepTimeGoal;
    }

    public boolean isExerciseTimeGoal() {
        return exerciseTimeGoal;
    }

    public void setExerciseTimeGoal(boolean exerciseTimeGoal) {
        this.exerciseTimeGoal = exerciseTimeGoal;
    }

    public boolean isHospital() {
        return hospital;
    }

    public void setHospital(boolean hospital) {
        this.hospital = hospital;
    }

    public Integer getHospitalDate() {
        return hospitalDate;
    }

    public void setHospitalDate(Integer hospitalDate) {
        this.hospitalDate = hospitalDate;
    }
}
