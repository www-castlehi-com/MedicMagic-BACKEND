package MedicMagic.userSymptom.domain;

public class UserSymptom {
    String id;
    String date;
    boolean none;
    boolean cramps;
    boolean breastTenderness;
    boolean headache;
    boolean acne;
    boolean lumbago;
    boolean nausea;
    boolean fatigue;
    boolean abdominalBloating;
    boolean desires;
    boolean insomnia;
    boolean constipation;
    boolean diarrhea;

    public UserSymptom(String id, String date, boolean none, boolean cramps, boolean breastTenderness, boolean headache, boolean acne, boolean lumbago, boolean nausea, boolean fatigue, boolean abdominalBloating, boolean desires, boolean insomnia, boolean constipation, boolean diarrhea) {
        this.id = id;
        this.date = date;
        this.none = none;
        this.cramps = cramps;
        this.breastTenderness = breastTenderness;
        this.headache = headache;
        this.acne = acne;
        this.lumbago = lumbago;
        this.nausea = nausea;
        this.fatigue = fatigue;
        this.abdominalBloating = abdominalBloating;
        this.desires = desires;
        this.insomnia = insomnia;
        this.constipation = constipation;
        this.diarrhea = diarrhea;
    }

    public UserSymptom() {
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

    public boolean isCramps() {
        return cramps;
    }

    public void setCramps(boolean cramps) {
        this.cramps = cramps;
    }

    public boolean isBreastTenderness() {
        return breastTenderness;
    }

    public void setBreastTenderness(boolean breastTenderness) {
        this.breastTenderness = breastTenderness;
    }

    public boolean isHeadache() {
        return headache;
    }

    public void setHeadache(boolean headache) {
        this.headache = headache;
    }

    public boolean isAcne() {
        return acne;
    }

    public void setAcne(boolean acne) {
        this.acne = acne;
    }

    public boolean isLumbago() {
        return lumbago;
    }

    public void setLumbago(boolean lumbago) {
        this.lumbago = lumbago;
    }

    public boolean isNausea() {
        return nausea;
    }

    public void setNausea(boolean nausea) {
        this.nausea = nausea;
    }

    public boolean isFatigue() {
        return fatigue;
    }

    public void setFatigue(boolean fatigue) {
        this.fatigue = fatigue;
    }

    public boolean isAbdominalBloating() {
        return abdominalBloating;
    }

    public void setAbdominalBloating(boolean abdominalBloating) {
        this.abdominalBloating = abdominalBloating;
    }

    public boolean isDesires() {
        return desires;
    }

    public void setDesires(boolean desires) {
        this.desires = desires;
    }

    public boolean isInsomnia() {
        return insomnia;
    }

    public void setInsomnia(boolean insomnia) {
        this.insomnia = insomnia;
    }

    public boolean isConstipation() {
        return constipation;
    }

    public void setConstipation(boolean constipation) {
        this.constipation = constipation;
    }

    public boolean isDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(boolean diarrhea) {
        this.diarrhea = diarrhea;
    }
}
