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

    public UserSymptomDto(String id, String date, String none, String cramps, String breastTenderness, String headache, String acne, String lumbago, String nausea, String fatigue, String abdominalBloating, String desires, String insomnia, String constipation, String diarrhea) {
        this.id = id;
        this.date=  date;
        if(none.equals("true")) {
            this.none = true;
        } else {
            this.none = false;
        }
        if(cramps.equals("true")) {
            this.cramps = true;
        } else {
            this.cramps = false;
        }
        if(breastTenderness.equals("true")) {
            this.breastTenderness = true;
        } else {
            this.breastTenderness = false;
        }
        if(headache.equals("true")) {
            this.headache = true;
        } else {
            this.headache = false;
        }
        if(acne.equals("true")) {
            this.acne = true;
        } else {
            this.acne = false;
        }
        if(lumbago.equals("true")) {
            this.lumbago = true;
        } else {
            this.lumbago = false;
        }
        if(nausea.equals("true")) {
            this.nausea = true;
        } else {
            this.nausea = false;
        }
        if(fatigue.equals("true")) {
            this.fatigue = true;
        } else {
            this.fatigue = false;
        }
        if(abdominalBloating.equals("true")) {
            this.abdominalBloating = true;
        } else {
            this.abdominalBloating = false;
        }
        if(desires.equals("true")) {
            this.desires = true;
        } else {
            this.desires = false;
        }
        if(insomnia.equals("true")) {
            this.insomnia = true;
        } else {
            this.insomnia = false;
        }
        if(constipation.equals("true")) {
            this.constipation = true;
        } else {
            this.constipation = false;
        }
        if(diarrhea.equals("true")) {
            this.diarrhea = true;
        } else {
            this.diarrhea = false;
        }
    }
}
