package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.AddressBook;

public interface AddressBookRepository extends JpaRepository<AddressBook,Long> {
}
