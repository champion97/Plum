package org.java10.plum.controllers;

import org.java10.plum.models.domain.Comment;
import org.java10.plum.models.param.CommentParam;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.CommentService;
import org.java10.plum.utils.CommentUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Tate on 2017/5/3.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;


    @ApiOperation(value="文章评论", notes = "只返回审核通过的评论")
    @GetMapping(value = "/{aid}")
    public RestResponse getArticleComment(@PathVariable("aid") Integer aid) {
        List<Comment> comments = commentService.getAuditedCommentsByArticleId(aid);
        return RestResponse.success(CommentUtil.buildCommentsTree(comments, true));
    }


    @ApiOperation(value="添加评论")
    @PostMapping
    public RestResponse addComment(@RequestBody @Valid CommentParam param, BindingResult result,
                                   @RequestHeader HttpHeaders headers) {

        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }

        Comment comment = new Comment();
        comment.setAid(param.getAid());
        comment.setParentId(param.getParentId());
        comment.setAuthor(param.getAuthor());
        comment.setText(param.getText());
        comment.setMail(param.getMail());
        comment.setUrl(param.getUrl());
        comment.setIp(headers.getFirst("X-Forwarded-For"));
        comment.setAgent(headers.getFirst("User-Agent"));
        comment.setAudited(false);
        comment.setCreatedAt(new Date());

        Comment commentResult = commentService.addComment(comment);

        return RestResponse.success(commentResult);
    }
}
