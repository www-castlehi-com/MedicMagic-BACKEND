package MedicMagic.userGoal.dto;

import MedicMagic.userGoal.domain.UserGoal;

public class UserGoalDto {
    public String id;
    public Double weigh;
    public String sleepTime;
    public String exerciseTime;
    public Double waterIntake;

    public UserGoalDto(UserGoal userGoal) {
        this.id = userGoal.getId();
        this.weigh = userGoal.getWeigh();
        this.sleepTime = userGoal.getSleepTime();
        this.exerciseTime = userGoal.getExerciseTime();
        this.waterIntake = userGoal.getWaterIntake();
    }
}
