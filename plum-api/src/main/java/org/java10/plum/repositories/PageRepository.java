package org.java10.plum.repositories;

import org.java10.plum.models.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tate<i@ridog.me>
 */
@Repository
public interface PageRepository  extends JpaRepository<Page, Integer> {

    Page findBySlug(String slug);
}
