package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.CategoryItem;

public interface CategoryItemRepository extends JpaRepository<CategoryItem,Long> {
}
