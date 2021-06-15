package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
