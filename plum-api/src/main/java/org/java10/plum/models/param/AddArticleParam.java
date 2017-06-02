package org.java10.plum.models.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Tate on 2017/3/6.
 */
@Data
public class AddArticleParam {

    @NotEmpty
    @Size(min = 1, max = 200)
    private String title;

    @NotEmpty
    @Size(min = 1)
    private String content;

    @URL
    private String cover;

    @Size(max = 50)
    private String slug;

    @NotNull
    private Boolean allowComment;

    @NotNull
    private Integer category;

    @NotNull
    private List<Integer> tags;

    @NotNull
    private Integer status;
}
