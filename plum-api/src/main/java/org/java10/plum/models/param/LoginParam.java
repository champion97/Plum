package org.java10.plum.models.param;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Tate<i@ridog.me>
 */
@Getter
@Setter
public class LoginParam {

    private String username;

    private String password;
}
