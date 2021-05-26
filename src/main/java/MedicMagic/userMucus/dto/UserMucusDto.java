package MedicMagic.userMucus.dto;

import MedicMagic.userMucus.domain.UserMucus;

public class UserMucusDto {
    public String id;
    public String date;
    public boolean none;
    public boolean mottled;
    public boolean sticky;
    public boolean creamy;
    public boolean likeEggWhite;
    public boolean watery;
    public boolean abnormal;

    public UserMucusDto(UserMucus userMucus) {
        this.id = userMucus.getId();
        this.date = userMucus.getDate();
        this.none = userMucus.isNone();
        this.mottled = userMucus.isMottled();
        this.sticky = userMucus.isSticky();
        this.creamy = userMucus.isCreamy();
        this.likeEggWhite = userMucus.isLikeEggWhite();
        this.watery = userMucus.isWatery();
        this.abnormal = userMucus.isAbnormal();
    }

    public UserMucusDto(String id, String date, String none, String mottled, String sticky, String creamy, String likeEggWhite, String watery, String abnormal) {
        this.id = id;
        this.date = date;
        if(none.equals("true")) {
            this.none = true;
        } else {
            this.none = false;
        }
        if(mottled.equals("true")) {
            this.mottled = true;
        } else {
            this.mottled = false;
        }
        if(sticky.equals("true")) {
            this.sticky = true;
        } else {
            this.sticky = false;
        }
        if(creamy.equals("true")) {
            this.creamy = true;
        } else {
            this.creamy = false;
        }
        if(likeEggWhite.equals("true")) {
            this.likeEggWhite = true;
        } else {
            this.likeEggWhite = false;
        }
        if(watery.equals("true")) {
            this.watery = true;
        } else {
            this.watery = false;
        }
        if(abnormal.equals("true")) {
            this.abnormal = true;
        } else {
            this.abnormal = false;
        }
    }
}
