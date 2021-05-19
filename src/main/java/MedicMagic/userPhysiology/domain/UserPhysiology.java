package MedicMagic.userPhysiology.domain;

public class UserPhysiology {
    String id;
    String startPhysiology;
    String endPhysiology;
    String expectedOvulationDate;
    String expectedPhysiologyDate;

    public UserPhysiology() {
    }

    public UserPhysiology(String id, String startPhysiology, String endPhysiology, String expectedOvulationDate, String expectedPhysiologyDate) {
        this.id = id;
        this.startPhysiology = startPhysiology;
        this.endPhysiology = endPhysiology;
        this.expectedOvulationDate = expectedOvulationDate;
        this.expectedPhysiologyDate = expectedPhysiologyDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartPhysiology() {
        return startPhysiology;
    }

    public void setStartPhysiology(String startPhysiology) {
        this.startPhysiology = startPhysiology;
    }

    public String getEndPhysiology() {
        return endPhysiology;
    }

    public void setEndPhysiology(String endPhysiology) {
        this.endPhysiology = endPhysiology;
    }

    public String getExpectedOvulationDate() {
        return expectedOvulationDate;
    }

    public void setExpectedOvulationDate(String expectedOvulationDate) {
        this.expectedOvulationDate = expectedOvulationDate;
    }

    public String getExpectedPhysiologyDate() {
        return expectedPhysiologyDate;
    }

    public void setExpectedPhysiologyDate(String expectedPhysiologyDate) {
        this.expectedPhysiologyDate = expectedPhysiologyDate;
    }
}
