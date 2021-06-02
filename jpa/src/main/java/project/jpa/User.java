package project.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthday;

}
