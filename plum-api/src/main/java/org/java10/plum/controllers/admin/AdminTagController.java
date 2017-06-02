package org.java10.plum.controllers.admin;

import org.java10.plum.controllers.BaseController;
import org.java10.plum.errors.ApiException;
import org.java10.plum.models.param.AddParam;
import org.java10.plum.models.param.ModifyParam;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tate<i@ridog.me>
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/tags")
public class AdminTagController extends BaseController{

    @Autowired
    private TagService tagService;

    @ApiOperation(value="admin-获取所有标签")
    @GetMapping
    public RestResponse getTags() {
        return RestResponse.success(tagService.getAll());
    }


    @ApiOperation(value="admin-新增标签")
    @PostMapping
    public RestResponse addTag(@RequestBody @Valid AddParam param, BindingResult result) {
        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }
        return RestResponse.success(tagService.addTag(param));
    }

    @ApiOperation(value="admin-修改标签")
    @PutMapping
    public RestResponse modifyTag(@RequestBody @Valid ModifyParam param, BindingResult result) {
        if (result.hasErrors()) {
            return buildErrors(result.getAllErrors());
        }
        return RestResponse.success(tagService.modifyTag(param));
    }

    @ApiOperation(value="admin-删除标签")
    @DeleteMapping("/{id:[0-9]+}")
    public RestResponse removeTag(@PathVariable Integer id) {
        if (id==null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        tagService.deleteTag(id);
        return RestResponse.success();
    }

}
