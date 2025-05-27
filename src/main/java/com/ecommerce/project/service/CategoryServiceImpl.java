package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<>();
    private Long categoryId = 1L;

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        category.setCategoryId(categoryId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream().
                filter(c -> c.getCategoryId()
                        .equals(categoryId))
                        .findFirst()
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
        categories.remove(category);
        return "Category deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> category1 = categories.stream().
                filter(c -> c.getCategoryId().equals(categoryId)).
                findFirst();

        if(category1.isPresent()){
            Category updateCategory = category1.get();
            updateCategory.setCategoryName(category.getCategoryName());
            return updateCategory;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
        }
    }
}
