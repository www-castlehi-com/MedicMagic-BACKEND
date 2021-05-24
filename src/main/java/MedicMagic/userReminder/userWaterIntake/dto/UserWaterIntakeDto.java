package MedicMagic.userReminder.userWaterIntake.dto;

import MedicMagic.userReminder.userWaterIntake.domain.UserWaterIntake;

public class UserWaterIntakeDto {
    public String id;
    public Double cups;
    public String waterTime;

    public UserWaterIntakeDto(UserWaterIntake userWaterIntake) {
        this.id = userWaterIntake.getId();
        this.cups = userWaterIntake.getCups();
        if(userWaterIntake.getWaterTime() == null) {
            waterTime = "22:00:00";
        } else {
            waterTime = userWaterIntake.getWaterTime();
        }
    }

    public UserWaterIntakeDto(String id, String cups, String waterTime){
        this.id = id;
        this.cups = Double.parseDouble(cups);
        if(waterTime.equals("null")) {
            waterTime = "22:00:00";
        } else {
            waterTime = waterTime;
        }
    }
}
