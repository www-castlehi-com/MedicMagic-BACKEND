package MedicMagic.userReminder.userReminderList.dto;

import MedicMagic.userReminder.userReminderList.domain.UserReminderList;

public class UserReminderListDto {
    public String id;
    public boolean birthControlPills;
    public boolean physiology;
    public boolean hospital;
    public boolean waterIntake;
    public boolean exerciseTimeGoal;
    public boolean sleepTimeGoal;

    public UserReminderListDto(UserReminderList userReminderList) {
        this.id = userReminderList.getId();
        this.birthControlPills = userReminderList.isBirthControlPills();
        this.physiology = userReminderList.isPhysiology();
        this.hospital = userReminderList.isHospital();
        this.waterIntake = userReminderList.isWaterIntake();
        this.exerciseTimeGoal = userReminderList.isExerciseTimeGoal();
        this.sleepTimeGoal = userReminderList.isSleepTimeGoal();
    }

    public UserReminderListDto(String id, String birthControlPills, String physiology, String hospital, String waterIntake, String exerciseTimeGoal, String sleepTimeGoal) {
        this.id = id;

        if(birthControlPills.equals("true")) {
            this.birthControlPills = true;
        } else {
            this.birthControlPills = false;
        }

        if(physiology.equals("true")) {
            this.physiology = true;
        } else {
            this.physiology = false;
        }

        if(hospital.equals("true")) {
            this.hospital = true;
        } else {
            this.hospital = false;
        }

        if(waterIntake.equals("true")) {
            this.waterIntake = true;
        } else {
            this.waterIntake = false;
        }

        if(exerciseTimeGoal.equals("true")) {
            this.exerciseTimeGoal = true;
        } else {
            this.exerciseTimeGoal = false;
        }

        if(sleepTimeGoal.equals("true")) {
            this.sleepTimeGoal = true;
        } else {
            this.sleepTimeGoal = false;
        }
    }
}
