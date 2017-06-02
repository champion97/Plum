package org.java10.plum.services;

import org.java10.plum.models.domain.Page;
import org.java10.plum.models.param.AddPageParam;
import org.java10.plum.models.param.ModifyPageParam;

import java.util.List;

/**
 * @author Tate<i@ridog.me>
 */
public interface PageService {

    Page getPageBySlug(String slug);

    Page getPageById(Integer id);

    Page addSinglePage(AddPageParam addPageParam);

    Page modifySinglePage(ModifyPageParam modifyPageParam);

    List<Page> getSinglePages();
}
