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
}
