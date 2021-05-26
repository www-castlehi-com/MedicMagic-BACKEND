package MedicMagic.userPhysiology.dto;

import MedicMagic.userPhysiology.domain.UserPhysiology;

public class UserPhysiologyDto {
    public String id;
    public String startPhysiology;
    public String endPhysiology;
    public String expectedOvulationDate;
    public String expectedPhysiologyDate;

    public UserPhysiologyDto(UserPhysiology userPhysiology) {
        this.id = userPhysiology.getId();
        this.startPhysiology = userPhysiology.getStartPhysiology();
        this.endPhysiology = userPhysiology.getEndPhysiology();
        this.expectedOvulationDate = userPhysiology.getExpectedOvulationDate();
        this.expectedPhysiologyDate = userPhysiology.getExpectedPhysiologyDate();
    }

    public UserPhysiologyDto(String id, String startPhysiology, String endPhysiology, String expectedOvulationDate, String expectedPhysiologyDate) {
        this.id = id;
        this.startPhysiology = startPhysiology;
        if(endPhysiology.equals("null")) {
            this.endPhysiology = null;
        } else {
            this.endPhysiology = endPhysiology;
        }
        if(expectedOvulationDate.equals("null")) {
            this.expectedOvulationDate = null;
        } else {
            this.expectedOvulationDate = expectedOvulationDate;
        }
        if(expectedPhysiologyDate.equals("null")) {
            this.expectedPhysiologyDate = null;
        } else {
            this.expectedPhysiologyDate = expectedPhysiologyDate;
        }
    }
}
