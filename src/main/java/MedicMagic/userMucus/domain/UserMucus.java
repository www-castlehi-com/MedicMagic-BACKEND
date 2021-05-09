package MedicMagic.userMucus.domain;

public class UserMucus {
    String id;
    String date;
    boolean none;
    boolean mottled;
    boolean sticky;
    boolean creamy;
    boolean likeEggWhite;
    boolean watery;
    boolean abnormal;

    public UserMucus(String id, String date, boolean none, boolean mottled, boolean sticky, boolean creamy, boolean likeEggWhite, boolean watery, boolean abnormal) {
        this.id = id;
        this.date = date;
        this.none = none;
        this.mottled = mottled;
        this.sticky = sticky;
        this.creamy = creamy;
        this.likeEggWhite = likeEggWhite;
        this.watery = watery;
        this.abnormal = abnormal;
    }

    public UserMucus() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isNone() {
        return none;
    }

    public void setNone(boolean none) {
        this.none = none;
    }

    public boolean isMottled() {
        return mottled;
    }

    public void setMottled(boolean mottled) {
        this.mottled = mottled;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public boolean isCreamy() {
        return creamy;
    }

    public void setCreamy(boolean creamy) {
        this.creamy = creamy;
    }

    public boolean isLikeEggWhite() {
        return likeEggWhite;
    }

    public void setLikeEggWhite(boolean likeEggWhite) {
        this.likeEggWhite = likeEggWhite;
    }

    public boolean isWatery() {
        return watery;
    }

    public void setWatery(boolean watery) {
        this.watery = watery;
    }

    public boolean isAbnormal() {
        return abnormal;
    }

    public void setAbnormal(boolean abnormal) {
        this.abnormal = abnormal;
    }
}
