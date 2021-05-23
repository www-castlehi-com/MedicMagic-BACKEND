package MedicMagic.user.dto;

import MedicMagic.user.domain.User;

public class UserDto {
    public String id;
    public String name;
    public String password;
    public String birthday;
    public Integer age;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.birthday = user.getBirthday();
        this.age = user.getAge();
    }

    public UserDto(String id, String name, String password, String birthday, String age) {
        if(id.equals("null")) {
            this.id = null;
        } else {
            this.id = id;
        }

        if(name.equals("null")) {
            this.name = null;
        } else {
            this.name = name;
        }

        if(password.equals("null")) {
            this.password = null;
        } else {
            this.password = password;
        }

        if(birthday.equals("null")) {
            this.birthday = null;
        } else {
            this.birthday = birthday;
        }

        if(age.equals("null")) {
            this.age = null;
        } else {
            this.age = Integer.parseInt(age);
        }
    }
}
