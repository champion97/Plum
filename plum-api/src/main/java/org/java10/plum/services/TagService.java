package org.java10.plum.services;

import org.java10.plum.models.domain.Tag;
import org.java10.plum.models.param.AddParam;
import org.java10.plum.models.param.ModifyParam;

import java.util.List;

/**
 * Created by Tate on 2017/2/27.
 */
public interface TagService {

    Tag addTag(AddParam addParam);

    List<Tag> getAll();

    Tag modifyTag(ModifyParam modifyParam);

    void deleteTag(Integer id);

    Tag getTag(Integer id);

}
