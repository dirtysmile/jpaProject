package project.jpa.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AddressBook {

    @Id @GeneratedValue
    @Column(name = "address_book_id")
    private Long id;

    @Embedded
    private Address address;
}
