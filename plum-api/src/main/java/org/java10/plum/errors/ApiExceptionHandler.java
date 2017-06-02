package org.java10.plum.errors;

import org.java10.plum.models.response.RestCode;
import org.java10.plum.models.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Tate on 2017/2/27.
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public RestResponse apiExceptionHandler(ApiException e) throws Exception {
        printStackTrace(e);
        return RestResponse.fail(e.getErrCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RestResponse runtimeExceptionHandler(RuntimeException e) throws Exception {
        printStackTrace(e);
        return RestResponse.fail(RestCode.FAIL.getCode(), RestCode.FAIL.getMsg());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public RestResponse dataIntegrityExceptionHandler(DataIntegrityViolationException e) throws Exception {
        printStackTrace(e);
        return RestResponse.fail(RestCode.DATA_OCCUPY_ERROR.getCode(), RestCode.DATA_OCCUPY_ERROR.getMsg());
    }

    private void printStackTrace(Exception ex) {
        log.error(ex.toString());
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement traceElement : trace)
            log.error("\tat " + traceElement);
    }


}
