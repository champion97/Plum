package org.java10.plum.repositories;

import org.java10.plum.models.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tate on 2017/2/27.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByAid(Integer aid);

    List<Comment> findByAidAndAudited(Integer aid, boolean audited);

    Page<Comment> findByAudited(Pageable pageable, boolean audited);

}
