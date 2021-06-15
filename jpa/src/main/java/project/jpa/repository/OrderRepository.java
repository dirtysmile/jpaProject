package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
