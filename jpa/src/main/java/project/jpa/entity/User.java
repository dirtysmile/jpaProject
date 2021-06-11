package project.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
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

}
