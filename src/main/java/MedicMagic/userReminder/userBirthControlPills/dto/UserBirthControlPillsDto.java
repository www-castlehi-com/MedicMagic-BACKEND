package MedicMagic.userReminder.userBirthControlPills.dto;

import MedicMagic.userReminder.userBirthControlPills.domain.UserBirthControlPills;

public class UserBirthControlPillsDto {
    public String id;
    public String pillsTime;
    public String pillsDate;
    public Integer days;

    public UserBirthControlPillsDto(UserBirthControlPills userBirthControlPills) {
        this.id = userBirthControlPills.getId();
        this.pillsTime = userBirthControlPills.getPillsTime();
        this.pillsDate = userBirthControlPills.getPillsDate();
        if(userBirthControlPills.getDays() == null) {
            this.days = 21;
        } else {
            this.days = userBirthControlPills.getDays();
        }
    }

    public UserBirthControlPillsDto(String id, String pillsTime, String pillsDate, String days) {
        this.id = id;

        if(pillsTime.equals("null")) {
            this.pillsTime = null;
        } else {
            this.pillsTime = pillsTime;
        }

        if(pillsDate.equals("null")) {
            this.pillsDate = null;
        } else {
            this.pillsDate = pillsDate;
        }

        if(days.equals("null")) {
            this.days = 21;
        } else {
            this.days = Integer.parseInt(days);
        }
    }
}
