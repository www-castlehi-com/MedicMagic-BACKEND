package MedicMagic.userReminder.userBirthControlPills.domain;

public class UserBirthControlPills {
    String id;
    String pillsTime;
    String pillsDate;
    Integer days;

    public UserBirthControlPills() {
    }

    public UserBirthControlPills(String id, String pillsTime, String pillsDate, Integer days) {
        this.id = id;
        this.pillsTime = pillsTime;
        this.pillsDate = pillsDate;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPillsTime() {
        return pillsTime;
    }

    public void setPillsTime(String pillsTime) {
        this.pillsTime = pillsTime;
    }

    public String getPillsDate() {
        return pillsDate;
    }

    public void setPillsDate(String pillsDate) {
        this.pillsDate = pillsDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
