package MedicMagic.userReminder.userHospital.dto;

import MedicMagic.userReminder.userHospital.domain.UserHospital;

public class UserHospitalDto {
    public String id;
    public String hospitalDate;
    public String hospitalTime;

    public UserHospitalDto(UserHospital userHospital) {
        this.id = userHospital.getId();
        if(userHospital.getHospitalDate() == null) {
            System.out.println("date is null");
            this.hospitalDate = "null";
        } else {
            this.hospitalDate = userHospital.getHospitalDate();
        }
        if(userHospital.getHospitalTime() == null) {
            this.hospitalTime = "18:00:00";
        } else {
            this.hospitalTime = userHospital.getHospitalTime();
        }
    }

    public UserHospitalDto(String id, String hospitalDate, String hospitalTime) {
        this.id = id;
        if(hospitalDate.equals("null")) {
            this.hospitalDate = null;
        } else {
            this.hospitalDate = hospitalDate;
        }
        if(hospitalTime.equals("null")) {
            this.hospitalTime = "18:00:00";
        } else {
            this.hospitalTime = hospitalTime;
        }
    }
}
