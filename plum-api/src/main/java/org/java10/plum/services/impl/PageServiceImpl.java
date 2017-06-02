package org.java10.plum.services.impl;

import org.java10.plum.models.domain.Page;
import org.java10.plum.models.param.AddPageParam;
import org.java10.plum.models.param.ModifyPageParam;
import org.java10.plum.repositories.PageRepository;
import org.java10.plum.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Tate<i@ridog.me>
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public Page getPageBySlug(String slug) {
        return pageRepository.findBySlug(slug);
    }

    @Override
    public Page getPageById(Integer id) {
        return pageRepository.getOne(id);
    }

    @Override
    public Page addSinglePage(AddPageParam addPageParam) {
        Page page = new Page();
        page.setTitle(addPageParam.getTitle());
        page.setContent(addPageParam.getContent());
        page.setSlug(addPageParam.getSlug());
        page.setCreatedAt(new Date());
        return pageRepository.save(page);
    }

    @Override
    public Page modifySinglePage(ModifyPageParam modifyPageParam) {
        Page page = pageRepository.findOne(modifyPageParam.getId());
        page.setTitle(modifyPageParam.getTitle());
        page.setContent(modifyPageParam.getContent());
        page.setSlug(modifyPageParam.getSlug());
        return pageRepository.save(page);
    }

    @Override
    public List<Page> getSinglePages() {
        return pageRepository.findAll();
    }
}
