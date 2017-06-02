package org.java10.plum.controllers;

import org.java10.plum.models.response.RestCode;
import org.java10.plum.models.response.RestResponse;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by Tate on 2017/4/19.
 */
public class BaseController {

    private ObjectError objectError;

    protected RestResponse buildErrors(List<ObjectError> errors) {
//        StringBuilder errorMsg = new StringBuilder();
//        for (final ObjectError objectError : errors) {
//            errorMsg.append(objectError.getDefaultMessage()).append(".");
//        }
        return RestResponse.fail(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
    }
}
