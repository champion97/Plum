package org.java10.plum.models.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Tate on 2017/4/19.
 */
@Data
public class ModifyArticleParam extends AddArticleParam {

    @NotNull
    private Integer id;
}
