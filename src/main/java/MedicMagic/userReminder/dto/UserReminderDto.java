package MedicMagic.userReminder.dto;

import MedicMagic.userReminder.domain.UserReminder;

public class UserReminderDto {
    public String id;
    public boolean birthControlPills;
    public Integer beforeBirthControlPills;
    public String birthControlPillsTime;
    public boolean physiology;
    public Integer beforePhysiology;
    public boolean sleepTimeGoal;
    public boolean exerciseTimeGoal;
    public boolean hospital;
    public Integer hospitalDate;

    public UserReminderDto(UserReminder userReminder) {
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
}
