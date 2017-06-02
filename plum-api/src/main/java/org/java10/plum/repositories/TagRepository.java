package org.java10.plum.repositories;

import org.java10.plum.models.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Tate on 2017/2/27.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    List<Tag> findByIdIn(Collection<Integer> ids);
}
