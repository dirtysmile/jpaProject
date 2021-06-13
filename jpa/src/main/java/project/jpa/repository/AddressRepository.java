package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
