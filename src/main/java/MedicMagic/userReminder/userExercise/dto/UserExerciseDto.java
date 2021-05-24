package MedicMagic.userReminder.userExercise.dto;

import MedicMagic.userReminder.userExercise.domain.UserExercise;

public class UserExerciseDto {
    public String id;
    public Double exerciseHour;

    public UserExerciseDto(UserExercise userExercise) {
        this.id = userExercise.getId();
        this.exerciseHour = userExercise.getExerciseHour();
    }

    public UserExerciseDto(String id, String exerciseHour) {
        this.id = id;
        this.exerciseHour = Double.parseDouble(exerciseHour);
    }
}
