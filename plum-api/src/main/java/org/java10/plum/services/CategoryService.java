package org.java10.plum.services;

import org.java10.plum.models.domain.Category;
import org.java10.plum.models.param.AddParam;
import org.java10.plum.models.param.ModifyParam;

import java.util.List;

/**
 * Created by Tate on 2017/3/6.
 */
public interface CategoryService {

    Category addCategory(AddParam addParam);

    List<Category> getAll();

    Category modifyCategory(ModifyParam modifyCategoryParam);

    void deleteCategory(Integer id);

    Category getCategory(Integer id);
}
