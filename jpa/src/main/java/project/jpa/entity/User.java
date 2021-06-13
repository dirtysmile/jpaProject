package project.jpa.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String personal_id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;



    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    public User(String personal_id, String password, String name, String phone, String email, LocalDate birthday, Gender gender) {
        this.personal_id = personal_id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }
}
