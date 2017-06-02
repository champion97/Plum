package org.java10.plum.models.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Tate on 2017/4/21.
 */
@Data
public class ModifyParam extends AddParam {

    @NotNull
    private Integer id;

}
