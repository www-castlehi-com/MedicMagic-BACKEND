package MedicMagic.userReminder.userSleep.dto;

import MedicMagic.userReminder.userSleep.domain.UserSleep;

public class UserSleepDto {
    public String id;
    public Double sleepGoal;

    public UserSleepDto(UserSleep userSleep) {
        this.id = userSleep.getId();
        this.sleepGoal = userSleep.getSleepGoal();
    }

    public UserSleepDto(String id, String sleepGoal) {
        this.id = id;
        this.sleepGoal = Double.parseDouble(sleepGoal);
    }
}
