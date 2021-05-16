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
}
