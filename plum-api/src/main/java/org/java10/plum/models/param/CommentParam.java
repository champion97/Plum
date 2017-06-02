package org.java10.plum.models.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Tate on 2017/5/3.
 */
@Data
public class CommentParam {

    @NotNull
    private Integer aid;

    private Integer parentId;

    @NotEmpty
    private String author;

    private String url;

    private String mail;

    @NotEmpty
    private String text;

}
