package project.jpa.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import project.jpa.dto.UpdateUserDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String personalId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;
    private int age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    public User(String personalId, String password, String name, String phone, String email, LocalDate birthday, Gender gender) {
        this.personalId = personalId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;

        this.calculateAge();
    }

    public User(Long id, String personalId, String password, String name, String phone, String email, LocalDate birthday, Gender gender) {
        this.id = id;
        this.personalId = personalId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }


    private void calculateAge(){
        LocalDate currentDate = LocalDate.now();
        if ((this.birthday != null) && (currentDate != null)) {
            this.age = Period.between(this.birthday, currentDate).getYears();
        } else {
            this.age = 0;
        }
    }

    public void updateUser(UpdateUserDto user){
        this.name = user.getName();
    }
}
