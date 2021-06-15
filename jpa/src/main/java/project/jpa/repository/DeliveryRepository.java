package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
