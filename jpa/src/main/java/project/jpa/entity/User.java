package project.jpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    }
}
