package MedicMagic.userReminder.userExercise.domain;

public class UserExercise {
    String id;
    Double exerciseHour;

    public UserExercise(String id, Double exerciseHour) {
        this.id = id;
        this.exerciseHour = exerciseHour;
    }

    public UserExercise() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getExerciseHour() {
        return exerciseHour;
    }

    public void setExerciseHour(Double exerciseHour) {
        this.exerciseHour = exerciseHour;
    }
}
