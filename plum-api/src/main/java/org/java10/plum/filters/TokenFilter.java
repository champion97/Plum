package org.java10.plum.filters;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.utils.JwtUtil;
import org.java10.plum.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Tate<i@ridog.me>
 */
@Slf4j
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            setAuthFailResponse(resp, RestCode.Unauthorized.getCode(), RestCode.Unauthorized.getMsg());
            return;
        }
        String token = authHeader.replaceAll("Bearer", "").trim();
        if (Strings.isNullOrEmpty(token)) {
            setAuthFailResponse(resp, RestCode.Unauthorized.getCode(), RestCode.Unauthorized.getMsg());
            return;
        }
        JwtUtil jwtUtil = SpringUtil.getBean("jwtUtil", JwtUtil.class);
        String usernameFromToken = jwtUtil.getUsernameFromToken(token);
        if (Strings.isNullOrEmpty(usernameFromToken)) {
            setAuthFailResponse(resp, RestCode.TOKENERROR.getCode(), RestCode.TOKENERROR.getMsg());
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return "OPTIONS".equals(request.getMethod());
    }

    private void setAuthFailResponse(HttpServletResponse response, int code, String msg) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(RestResponse.fail(code, msg)));
        writer.flush();
        writer.close();
    }
}
