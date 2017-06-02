package org.java10.plum.repositories;

import org.java10.plum.models.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tate on 2017/2/27.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
