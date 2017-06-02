package org.java10.plum.models.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @author Tate<i@ridog.me>
 */
@Data
public class AddPageParam {

    @NotEmpty
    @Size(min = 1, max = 200)
    private String title;

    @NotEmpty
    @Size(min = 1)
    private String content;

    @Size(max = 50)
    private String slug;

}
