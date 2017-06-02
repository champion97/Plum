package org.java10.plum.services;

import org.java10.plum.models.domain.Article;
import org.java10.plum.models.domain.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Tate on 2017/3/14.
 */
public interface CommentService {

    List<Comment> getAuditedCommentsByArticleId(Integer aid);

    Comment addComment(Comment comment);

    void sendMail(Comment comment, Article article);

    Comment auditComment(Integer cid);

    Page<Comment> getCommentsByStatus(int pageNum, int pageSize, Boolean audited);

    void deleteComment(Integer cid);

}
