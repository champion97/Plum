package org.java10.plum.services.impl;

import org.java10.plum.errors.ApiException;
import org.java10.plum.models.MailProperties;
import org.java10.plum.models.domain.Article;
import org.java10.plum.models.domain.Comment;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.repositories.ArticleRepository;
import org.java10.plum.repositories.CommentRepository;
import org.java10.plum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tate on 2017/3/14.
 */
@Service
@EnableAsync
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MailProperties properties;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Comment> getAuditedCommentsByArticleId(Integer aid) {
        return commentRepository.findByAidAndAudited(aid, true);
    }

    @Override
    public Comment addComment(Comment comment) {
        Comment commentResult = commentRepository.save(comment);
        this.sendMail(commentResult, articleRepository.getOne(commentResult.getAid()));
        return commentResult;
    }

    @Override
    public Comment auditComment(Integer cid) {
        Comment comment = commentRepository.findOne(cid);
        if (comment==null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        comment.setAudited(true);
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> getCommentsByStatus(int pageNum, int pageSize, Boolean audited) {
        if (audited==null) {
            return commentRepository.findAll(new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"));
        }
        return commentRepository.findByAudited(new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"), audited);
    }

    @Override
    public void deleteComment(Integer cid) {
        commentRepository.delete(cid);
    }

    @Override
    @Async
    // 异步发送邮件
    public void sendMail(Comment comment, Article article) {
        if (properties.getNotification()) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(properties.getFrom());
            message.setTo(properties.getTo());
            message.setSubject(properties.getSubject());
            String messageText = properties.getText()
                    .replace("author", comment.getAuthor())
                    .replace("title", article.getTitle())
                    .replace("text", comment.getText())
                    .replace("agent", comment.getAgent())
                    .replace("ip", Optional.ofNullable(comment.getIp()).orElse(""));
            message.setText(messageText);
            mailSender.send(message);
        }
    }
}
