package org.java10.plum.models.param;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Tate on 2017/4/11.
 */

@Getter
@Setter
public class SearchArticleParam {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String keyword;

    public Integer getPageNum() {
        return pageNum < 1 ? 0 : pageNum - 1;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
