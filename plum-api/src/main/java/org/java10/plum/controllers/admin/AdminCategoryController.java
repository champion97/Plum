package org.java10.plum.controllers.admin;

import org.java10.plum.controllers.BaseController;
import org.java10.plum.errors.ApiException;
import org.java10.plum.models.param.AddParam;
import org.java10.plum.models.param.ModifyParam;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by tate  on 2017/2/27.
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value="admin-获取所有分类")
    @GetMapping
    public RestResponse getCategories() {
        return RestResponse.success(categoryService.getAll());
    }


    @ApiOperation(value="admin-新增分类")
    @PostMapping
    public RestResponse addCategory(@RequestBody @Valid AddParam param, BindingResult result) {
        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }
        return RestResponse.success(categoryService.addCategory(param));
    }

    @ApiOperation(value="admin-修改分类")
    @PutMapping
    public RestResponse modifyCategory(@RequestBody @Valid ModifyParam param, BindingResult result) {
        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }
        return RestResponse.success(categoryService.modifyCategory(param));
    }

    @ApiOperation(value="admin-删除分类")
    @DeleteMapping("/{id:[0-9]+}")
    public RestResponse removeCategory(@PathVariable Integer id) {
        if (id==null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        categoryService.deleteCategory(id);
        return RestResponse.success();
    }

}
