package project.jpa.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class User extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

}
