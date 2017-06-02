package org.java10.plum.repositories;

import org.java10.plum.models.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tate on 2017/2/27.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findArticlesByContentContainingOrTitleContaining(String title, String content);

    Page<Article> findByTitleContaining(Pageable pageable, String title);

    Page<Article> findByStatus(Pageable pageable, Integer status);

}
