package org.java10.plum.controllers;

import org.java10.plum.models.response.RestResponse;
import org.java10.plum.services.PageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tate<i@ridog.me>
 */
@RestController
@RequestMapping("/api/page")
public class SinglePageController {
    @Autowired
    private PageService pageService;

    @ApiOperation(value = "单页面")
    @GetMapping("/{slug}")
    public RestResponse getSinglePage(@PathVariable("slug") String slug) {
        return RestResponse.success(pageService.getPageBySlug(slug));
    }
}
