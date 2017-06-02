package org.java10.plum.controllers.admin;

import org.java10.plum.errors.ApiException;
import org.java10.plum.models.param.LoginParam;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.models.response.RestResponse;
import org.java10.plum.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tate<i@ridog.me>
 */
@RestController
@RequestMapping("/api/account")
public class AdminAccountController {

    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public RestResponse login(@RequestBody LoginParam loginParam){

        if (loginParam.getUsername() == null || loginParam.getPassword() == null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }

        if (!username.equals(loginParam.getUsername()) || !password.equals(loginParam.getPassword())) {
            throw new ApiException(RestCode.UserNamePasswordError.getCode(), RestCode.UserNamePasswordError.getMsg());
        }

        // 如果是多用户的话，需要将token进行持久化并设置时效，方便控制用户
        String token = jwtUtil.generateToken(username);

        return RestResponse.success(token);
    }
}
