package pione.com.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface blogRepository extends JpaRepository<blog,Integer> {
    List<blog> findAllByCategoryId(Integer categoryId);
}
