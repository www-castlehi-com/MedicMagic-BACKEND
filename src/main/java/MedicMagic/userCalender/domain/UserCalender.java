package MedicMagic.userCalender.domain;


public class UserCalender {
    String id;
    String date;
    Double weigh;
    String sleepTime;
    String exerciseTime;
    Double waterIntake;
    String startDay;
    String endDay;
    String emotion;
    boolean symptom;

    public static class Builder {
        String id;
        String date;
        Double weigh;
        String sleepTime;
        String exerciseTime;
        Double waterIntake;
        String startDay;
        String endDay;
        String emotion;
        boolean symptom;

        public Builder(String id, String date) {
            this.id = id;
            this.date = date;
        }

        public Builder setWeigh(double weigh) {
            this.weigh = weigh;
            return this;
        }

        public Builder setSleepTime(String sleepTime) {
            this.sleepTime = sleepTime;
            return this;
        }

        public Builder setExerciseTime(String exerciseTime) {
            this.exerciseTime = exerciseTime;
            return this;
        }

        public Builder setWaterIntake(double waterIntake) {
            this.waterIntake = waterIntake;
            return this;
        }

        public Builder setStartDay(String startDay) {
            this.startDay = startDay;
            return this;
        }

        public Builder setEndDay(String endDay) {
            this.endDay = endDay;
            return this;
        }

        public Builder setEmotion(String emotion) {
            this.emotion = emotion;
            return this;
        }

        public Builder setSymptom(boolean symptom) {
            this.symptom = symptom;
            return this;
        }


        public UserCalender build() {
            UserCalender userCalender = new UserCalender();
            userCalender.id = id;
            userCalender.date = date;
            userCalender.weigh = weigh;
            userCalender.sleepTime = sleepTime;
            userCalender.exerciseTime = exerciseTime;
            userCalender.waterIntake = waterIntake;
            userCalender.startDay = startDay;
            userCalender.endDay = endDay;
            userCalender.emotion = emotion;
            userCalender.symptom = symptom;
            return userCalender;
        }
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

    public Double getWeigh() {
        return weigh;
    }

    public void setWeigh(Double weigh) {
        this.weigh = weigh;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Double getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(Double waterIntake) {
        this.waterIntake = waterIntake;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public boolean isSymptom() {
        return symptom;
    }

    public void setSymptom(boolean symptom) {
        this.symptom = symptom;
    }
}
