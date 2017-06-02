package org.java10.plum.services;

import org.java10.plum.models.domain.Article;
import org.java10.plum.models.param.AddArticleParam;
import org.java10.plum.models.param.ModifyArticleParam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Tate on 2017/3/1.
 */
public interface ArticleService {

    Article addArticle(AddArticleParam addArticleParam);

    Article modifyArticle(ModifyArticleParam modifyArticleParam);

    void increaseCommentNum(Integer id);

    void reduceCommentNum(Integer id);

    void deleteArticle(Integer id);

    Page<Article> getArticleByPage(int pageNum, int pageSize);

    Page<Article> getArticleByTitle(int pageNum, int pageSize, String title);

    Article getArticle(Integer id);

    List<Article> searchArticle(String keyword);

    Page<Article> getPublishedArticleByPage(int pageNum, int pageSize);

    Article modifyArticleStatus(Integer id, Integer status);



}
