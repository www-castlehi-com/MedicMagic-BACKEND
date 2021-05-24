package MedicMagic.userReminder.userReminderPhysiology.dto;

import MedicMagic.userReminder.userReminderPhysiology.domain.UserReminderPhysiology;

public class UserReminderPhysiologyDto {
    public String id;
    public String physiologyTime;

    public UserReminderPhysiologyDto(UserReminderPhysiology userPhysiology) {
        this.id = userPhysiology.getId();
        if(userPhysiology.getPhysiologyTime() == null) {
            this.physiologyTime = "18:00:00";
        } else {
            this.physiologyTime = userPhysiology.getPhysiologyTime();
        }
    }

    public UserReminderPhysiologyDto(String id, String physiologyTime) {
        this.id = id;
        if(physiologyTime.equals("null")) {
            this.physiologyTime = "18:00:00";
        } else {
            this.physiologyTime = physiologyTime;
        }
    }
}
