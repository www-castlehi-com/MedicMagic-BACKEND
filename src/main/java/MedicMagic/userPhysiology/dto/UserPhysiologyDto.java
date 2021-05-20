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
        this.endPhysiology = userPhysiology.getExpectedOvulationDate();
        this.expectedPhysiologyDate = userPhysiology.getExpectedPhysiologyDate();
    }
}
