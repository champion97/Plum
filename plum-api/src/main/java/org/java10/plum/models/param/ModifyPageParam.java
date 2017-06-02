package org.java10.plum.models.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Tate<i@ridog.me>
 */
@Data
public class ModifyPageParam extends AddPageParam{

    @NotNull
    private Integer id;

}
