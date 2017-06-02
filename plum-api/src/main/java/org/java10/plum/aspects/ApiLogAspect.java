package org.java10.plum.aspects;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Tate on 2017/3/15.
 */
@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    @Pointcut("execution(public * org.java10.plum.controllers..*.*(..)))")
    private void api(){}

    @Before("api()")
    private void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Map<String, Object> requestAsMap = getRequestAsMap(request);

        log.info("classMethod:{},arguments:{}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        log.info("Request:"+requestAsMap.toString());

    }
    private Map<String, Object> getRequestAsMap(HttpServletRequest request){
        TreeMap<String, Object> map = Maps.newTreeMap();
        map.put("IP", request.getRemoteAddr());
        map.put("URI", request.getRequestURI());
        map.put("method", request.getMethod());
        map.put("queryString", request.getQueryString());
        return map;
    }

}
