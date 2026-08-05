package spring.test_task.service;

import spring.test_task.dto.CategoryDto;
import spring.test_task.dto.CreateCategoryRequestDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CreateCategoryRequestDto request);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(Long id, CreateCategoryRequestDto request);

    void deleteCategory(Long id);

}
