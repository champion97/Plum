package org.java10.plum.services.impl;

import org.java10.plum.errors.ApiException;
import org.java10.plum.models.domain.Category;
import org.java10.plum.models.param.AddParam;
import org.java10.plum.models.param.ModifyParam;
import org.java10.plum.models.response.RestCode;
import org.java10.plum.repositories.CategoryRepository;
import org.java10.plum.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tate on 2017/3/6.
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(AddParam addParam) {
        Category category = new Category();
        category.setName(addParam.getName());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    @Override
    public Category modifyCategory(ModifyParam modifyCategoryParam) {
        Category category = categoryRepository.findOne(modifyCategoryParam.getId());
        category.setName(modifyCategoryParam.getName());
        if (category==null) {
            throw new ApiException(RestCode.PARAMETERERROR.getCode(), RestCode.PARAMETERERROR.getMsg());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.getOne(id);
    }
}
