package MedicMagic.userReminder.userWaterIntake.domain;

public class UserWaterIntake {
    String id;
    Double cups;
    String waterTime;

    public UserWaterIntake(String id, Double cups, String waterTime) {
        this.id = id;
        this.cups = cups;
        this.waterTime = waterTime;
    }

    public UserWaterIntake() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCups() {
        return cups;
    }

    public void setCups(Double cups) {
        this.cups = cups;
    }

    public String getWaterTime() {
        return waterTime;
    }

    public void setWaterTime(String waterTime) {
        this.waterTime = waterTime;
    }
}
