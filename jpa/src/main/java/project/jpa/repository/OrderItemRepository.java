package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
