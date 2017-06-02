package org.java10.plum.controllers.admin;

import com.google.common.base.Strings;
import org.java10.plum.controllers.BaseController;
import org.java10.plum.errors.ApiException;
import org.java10.plum.models.domain.Article;
import org.java10.plum.models.param.AddArticleParam;
import org.java10.plum.models.param.ModifyArticleParam;
import org.java10.plum.models.param.SearchArticleParam;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by Tate on 2017/2/27.
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/articles")
public class AdminArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value="admin-获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "查询关键字", required = false),
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue="1", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页大小", defaultValue="10",required = false)
    })
    @GetMapping
    public RestResponse getArticles(@ModelAttribute SearchArticleParam searchArticleParam) {
        Page<Article> articles = null;
        if (Strings.isNullOrEmpty(searchArticleParam.getKeyword())) {
            articles =  articleService.getArticleByPage(searchArticleParam.getPageNum(), searchArticleParam.getPageSize());
        }else {
            articles =  articleService.getArticleByTitle(searchArticleParam.getPageNum(), searchArticleParam.getPageSize(),
                    searchArticleParam.getKeyword());
        }
        return RestResponse.success(articles);
    }

    @ApiOperation(value="admin-获取单个文章")
    @GetMapping("/{id:[0-9]+}")
    public RestResponse getArticle(@PathVariable Integer id) {
        if (id==null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        return RestResponse.success(articleService.getArticle(id));
    }

    @ApiOperation(value="admin-新增文章")
    @PostMapping
    public RestResponse addArticle(@RequestBody @Valid AddArticleParam param, BindingResult result) {
        if (result.hasErrors()) {
           return buildErrors(result.getAllErrors());
        }
        return RestResponse.success(articleService.addArticle(param));
    }

    @ApiOperation(value="admin-修改文章")
    @PutMapping
    public RestResponse modifyArticle(@RequestBody @Valid ModifyArticleParam param, BindingResult result) {
        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }

        return RestResponse.success(articleService.modifyArticle(param));
    }

    @ApiOperation(value="admin-删除文章")
    @DeleteMapping("/{id:[0-9]+}")
    public RestResponse removeArticle(@PathVariable Integer id) {
        if (id==null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        articleService.deleteArticle(id);
        return RestResponse.success();
    }

    @ApiOperation(value="admin-更改文章状态")
    @PutMapping("/{id:[0-9]+}/{status:[1-2]}")
    public RestResponse modifyArticleStatus(@PathVariable("id") Integer id,
                                            @PathVariable("status") Integer status) {
        return RestResponse.success(articleService.modifyArticleStatus(id, status));
    }

}
