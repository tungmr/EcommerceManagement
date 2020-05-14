package com.tungmr.utils;

import com.tungmr.entity.CategoryEntity;
import com.tungmr.model.Category;

public class CategoryUtils {

    public static CategoryEntity dto2Entity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        if (category != null) {
            entity.setCategoryId(category.getCategoryId());
            entity.setCategoryName(category.getCategoryName());
        }
        return entity;
    }

    public static Category entity2DTO(CategoryEntity entity) {
        Category dto = new Category();
        if (entity != null) {
            dto.setCategoryId(entity.getCategoryId());
            dto.setCategoryName(entity.getCategoryName());
        }
        return dto;
    }
}
