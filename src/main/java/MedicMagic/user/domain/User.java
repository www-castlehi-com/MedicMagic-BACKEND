package MedicMagic.user.domain;

public class User {
    String id;
    String name;
    String password;
    String birthday;
    Integer age;

    public User(String id, String name, String password, String birthday, Integer age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.age = age;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
