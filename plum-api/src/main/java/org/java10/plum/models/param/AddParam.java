package org.java10.plum.models.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Tate on 2017/4/21.
 */
@Data
public class AddParam {

    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;
}
