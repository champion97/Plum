package org.java10.plum.services.impl;

import org.java10.plum.errors.ApiException;
import org.java10.plum.models.domain.Article;
import org.java10.plum.models.domain.Category;
import org.java10.plum.models.domain.Tag;
import org.java10.plum.models.param.AddArticleParam;
import org.java10.plum.models.param.ModifyArticleParam;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.repositories.ArticleRepository;
import org.java10.plum.repositories.CategoryRepository;
import org.java10.plum.repositories.TagRepository;
import org.java10.plum.services.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Tate on 2017/3/1.
 */
@Slf4j
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Article addArticle(AddArticleParam addArticleParam) {
        Article article = new Article();
        article.setTitle(addArticleParam.getTitle());
        article.setCover(addArticleParam.getCover());
        article.setContent(addArticleParam.getContent());
        article.setCommentNum(0);
        article.setSlug(addArticleParam.getSlug());
        article.setStatus(addArticleParam.getStatus());
        article.setAllowComment(addArticleParam.getAllowComment());
        article.setCreatedAt(new Date());

        // 分类
        Category category = categoryRepository.findOne(addArticleParam.getCategory());
        if (category == null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        article.setCategory(category);

        // 标签
        List<Tag> dbTags = tagRepository.findByIdIn(addArticleParam.getTags());
        if (dbTags.size() != addArticleParam.getTags().size()) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        Set<Tag> set = new HashSet<>();
        set.addAll(dbTags);
        article.setTags(set);

        return articleRepository.save(article);
    }

    @Override
    public Article modifyArticle(ModifyArticleParam modifyArticleParam) {
        Article article = articleRepository.findOne(modifyArticleParam.getId());
        if (article == null) {
            throw new ApiException(RestCode.ARTICLE_NOT_FOUND.getCode(), RestCode.ARTICLE_NOT_FOUND.getMsg());
        }
        article.setTitle(modifyArticleParam.getTitle());
        article.setContent(modifyArticleParam.getContent());
        article.setCover(modifyArticleParam.getCover());
        article.setCommentNum(0);
        article.setSlug(modifyArticleParam.getSlug());
        article.setStatus(modifyArticleParam.getStatus());
        article.setAllowComment(modifyArticleParam.getAllowComment());
        article.setCreatedAt(new Date());

        // 分类
        Category category = categoryRepository.findOne(modifyArticleParam.getCategory());
        if (category == null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        article.setCategory(category);

        // 标签
        List<Tag> dbTags = tagRepository.findByIdIn(modifyArticleParam.getTags());
        if (dbTags.size() != modifyArticleParam.getTags().size()) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        Set<Tag> set = new HashSet<>();
        set.addAll(dbTags);
        article.setTags(set);

        return articleRepository.save(article);
    }

    @Override
    public synchronized void increaseCommentNum(Integer id) {
        Article article = this.getArticle(id);
        article.setCommentNum(article.getCommentNum() + 1);
    }

    @Override
    public synchronized void reduceCommentNum(Integer id) {
        Article article = this.getArticle(id);
        article.setCommentNum(article.getCommentNum() - 1);
    }

    @Override
    public void deleteArticle(Integer id) {
        log.info("删除文章,id:{}", id);
        articleRepository.delete(id);
    }

    @Override
    public Page<Article> getArticleByPage(int pageNum, int pageSize) {
        Page<Article> articles = articleRepository.findAll(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"));
        return articles;
    }

    @Override
    public Page<Article> getArticleByTitle(int pageNum, int pageSize, String title) {
        Page<Article> articles = articleRepository.findByTitleContaining(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"), title);
        return articles;
    }

    @Override
    public Article getArticle(Integer id) {
        log.info("查询文章,id:{}", id);
        Article article = articleRepository.findOne(id);
        if (article == null) {
            throw new ApiException(RestCode.ARTICLE_NOT_FOUND.getCode(), RestCode.ARTICLE_NOT_FOUND.getMsg());
        }
        return article;
    }

    @Override
    public List<Article> searchArticle(String keyword) {
        log.info("查询文章,关键字:{}", keyword);
        List<Article> articles = articleRepository.
                findArticlesByContentContainingOrTitleContaining(keyword, keyword);
        return articles;
    }

    @Override
    public Page<Article> getPublishedArticleByPage(int pageNum, int pageSize) {
        Page<Article> articles = articleRepository.findByStatus(
                new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"), 2);
        articles.forEach(article -> {
            int moreIndex = article.getContent().indexOf("<!--more-->");
            if (moreIndex > 0) {
                article.setContent(article.getContent().substring(0, moreIndex));
            }
        });
        return articles;
    }

    @Override
    public Article modifyArticleStatus(Integer id, Integer status) {
        if (id == null || status == null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        Article article = articleRepository.findOne(id);
        if (article == null) {
            throw new ApiException(RestCode.ARTICLE_NOT_FOUND.getCode(), RestCode.ARTICLE_NOT_FOUND.getMsg());
        }
        article.setStatus(status);
        return articleRepository.save(article);
    }

}
