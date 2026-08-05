package spring.test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.test_task.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
