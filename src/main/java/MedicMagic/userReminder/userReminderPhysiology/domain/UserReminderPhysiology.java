package MedicMagic.userReminder.userReminderPhysiology.domain;

public class UserReminderPhysiology {
    String id;
    String physiologyTime;

    public UserReminderPhysiology(String id, String physiologyTime) {
        this.id = id;
        this.physiologyTime = physiologyTime;
    }

    public UserReminderPhysiology() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhysiologyTime() {
        return physiologyTime;
    }

    public void setPhysiologyTime(String physiologyTime) {
        this.physiologyTime = physiologyTime;
    }
}
