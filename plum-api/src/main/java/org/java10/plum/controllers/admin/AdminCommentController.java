package org.java10.plum.controllers.admin;

import org.java10.plum.controllers.BaseController;
import org.java10.plum.models.domain.Comment;
import org.java10.plum.models.param.SearchCommentParam;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.ArticleService;
import org.java10.plum.services.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tate<i@ridog.me>
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/comments")
public class AdminCommentController extends BaseController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "admin-获取评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页大小", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "audited", value = "是否审核通过", required = false)
    })
    @GetMapping
    public RestResponse getComments(@ModelAttribute SearchCommentParam searchCommentParam) {
        Page<Comment> commentsByStatus = commentService.getCommentsByStatus(searchCommentParam.getPageNum(), searchCommentParam.getPageSize(),
                searchCommentParam.getAudited());
        return RestResponse.success(commentsByStatus);
    }

    @ApiOperation(value = "admin-审核评论")
    @PutMapping("audit/{cid:[0-9]+}")
    public RestResponse auditComment(@PathVariable Integer cid) {
        Comment comment = commentService.auditComment(cid);
        articleService.increaseCommentNum(comment.getAid());
        return RestResponse.success("审核通过", comment);
    }

    @ApiOperation(value = "admin-删除评论")
    @DeleteMapping("{cid:[0-9]+}")
    public RestResponse removeComment(@PathVariable Integer cid) {
        commentService.deleteComment(cid);
        Comment comment = commentService.auditComment(cid);
        articleService.reduceCommentNum(comment.getAid());
        return RestResponse.success();
    }
}
