package org.java10.plum.services.impl;

import org.java10.plum.errors.ApiException;
import org.java10.plum.models.domain.Tag;
import org.java10.plum.models.param.AddParam;
import org.java10.plum.models.param.ModifyParam;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.repositories.TagRepository;
import org.java10.plum.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tate on 2017/2/27.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @Override
    public Tag getTag(Integer id) {
        return tagRepository.getOne(id);
    }

    @Override
    public Tag addTag(AddParam addParam) {
        Tag tag = new Tag();
        tag.setName(addParam.getName());
        return tagRepository.save(tag);
    }

    @Override
    public Tag modifyTag(ModifyParam modifyParam) {
        Tag tag = tagRepository.findOne(modifyParam.getId());
        if (tag==null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        tag.setName(modifyParam.getName());
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        tagRepository.delete(id);
    }
}
