package project.jpa.dto;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import project.jpa.entity.Gender;
import project.jpa.entity.User;

import java.time.LocalDate;

@Data
public class UserRequestDto {
    private Long user_id;
    private String personalId;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
    private Gender gender;

    public UserRequestDto(User user) {
        this.user_id = user.getId();
        this.personalId = user.getPersonalId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.gender = user.getGender();
    }
}
