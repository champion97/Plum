package org.java10.plum.controllers;

import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.ArticleService;
import org.java10.plum.services.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tate on 2017/2/27.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "文章列表", notes = "默认最多返回5条数据")
    @GetMapping
    public RestResponse getArticles(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "size", defaultValue = "5") int pageSize) {
        pageNum = pageNum < 1 ? 0 : pageNum - 1;
        return RestResponse.success(articleService.getPublishedArticleByPage(pageNum, pageSize));
    }

    @ApiOperation(value = "文章详情")
    @GetMapping("/{id}")
    public RestResponse getArticle(@PathVariable("id") Integer id) {
        return RestResponse.success(articleService.getArticle(id));
    }

}
