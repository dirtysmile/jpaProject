package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
