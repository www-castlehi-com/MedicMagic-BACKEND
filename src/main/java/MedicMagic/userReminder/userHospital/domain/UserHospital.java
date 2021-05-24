package MedicMagic.userReminder.userHospital.domain;

public class UserHospital {
    String id;
    String hospitalDate;
    String hospitalTime;

    public UserHospital(String id, String hospitalDate, String hospitalTime) {
        this.id = id;
        this.hospitalDate = hospitalDate;
        this.hospitalTime = hospitalTime;
    }

    public UserHospital() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalDate() {
        return hospitalDate;
    }

    public void setHospitalDate(String hospitalDate) {
        this.hospitalDate = hospitalDate;
    }

    public String getHospitalTime() {
        return hospitalTime;
    }

    public void setHospitalTime(String hospitalTime) {
        this.hospitalTime = hospitalTime;
    }
}
