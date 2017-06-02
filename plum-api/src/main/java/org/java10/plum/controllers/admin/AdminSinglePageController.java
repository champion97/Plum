package org.java10.plum.controllers.admin;

import org.java10.plum.controllers.BaseController;
import org.java10.plum.models.param.AddPageParam;
import org.java10.plum.models.param.ModifyPageParam;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.PageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tate<i@ridog.me>
 */
@RestController
@RequestMapping("/api/admin/pages")
public class AdminSinglePageController extends BaseController {

    @Autowired
    private PageService pageService;


    @ApiOperation(value="admin-获取单页面")
    @GetMapping("")
    public RestResponse getPages() {
        return RestResponse.success(pageService.getSinglePages());
    }

    @ApiOperation(value="admin-获取单页面")
    @GetMapping("/{id}")
    public RestResponse getPage(@PathVariable("id") Integer id) {
        return RestResponse.success(pageService.getPageById(id));
    }

    @ApiOperation(value="admin-新增单页面")
    @PostMapping
    public RestResponse addPage(@RequestBody @Valid AddPageParam param, BindingResult result) {
        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }
        return RestResponse.success(pageService.addSinglePage(param));
    }

    @ApiOperation(value="admin-编辑单页面")
    @PutMapping
    public RestResponse modifyPage(@RequestBody @Valid ModifyPageParam param, BindingResult result) {
        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }
        return RestResponse.success(pageService.modifySinglePage(param));
    }

}
