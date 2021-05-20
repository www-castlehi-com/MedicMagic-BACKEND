package MedicMagic.userSymptom.dto;

import MedicMagic.userSymptom.domain.UserSymptom;

public class UserSymptomDto {
    public String id;
    public String date;
    public boolean none;
    public boolean cramps;
    public boolean breastTenderness;
    public boolean headache;
    public boolean acne;
    public boolean lumbago;
    public boolean nausea;
    public boolean fatigue;
    public boolean abdominalBloating;
    public boolean desires;
    public boolean insomnia;
    public boolean constipation;
    public boolean diarrhea;

    public UserSymptomDto(UserSymptom userSymptom) {
        this.id = userSymptom.getId();
        this.date = userSymptom.getDate();
        this.none = userSymptom.isNone();
        this.cramps = userSymptom.isCramps();
        this.breastTenderness = userSymptom.isBreastTenderness();
        this.headache = userSymptom.isHeadache();
        this.acne = userSymptom.isAcne();
        this.lumbago = userSymptom.isLumbago();
        this.nausea = userSymptom.isNausea();
        this.fatigue = userSymptom.isFatigue();
        this.abdominalBloating = userSymptom.isAbdominalBloating();
        this.desires = userSymptom.isDesires();
        this.insomnia = userSymptom.isInsomnia();
        this.constipation = userSymptom.isConstipation();
        this.diarrhea = userSymptom.isDiarrhea();
    }
}
