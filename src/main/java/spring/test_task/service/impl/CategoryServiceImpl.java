package spring.test_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.test_task.dto.CategoryDto;
import spring.test_task.entity.Category;
import spring.test_task.dto.CreateCategoryRequestDto;
import spring.test_task.exception.DuplicateEntityException;
import spring.test_task.exception.EntityNotFoundException;
import spring.test_task.mapper.CategoryMapper;
import spring.test_task.repository.CategoryRepository;
import spring.test_task.service.CategoryService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Category not found";
    private static final String DUPLICATE_ENTITY_EXCEPTION_MESSAGE = "Category already exists";

    @Override
    @Transactional
    public CategoryDto createCategory(CreateCategoryRequestDto request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new DuplicateEntityException(DUPLICATE_ENTITY_EXCEPTION_MESSAGE);
        } else {
            Category category = categoryRepository.save(categoryMapper.toEntity(request));
            return categoryMapper.toDto(category);

        }
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE)));
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CreateCategoryRequestDto request) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        categoryMapper.updateEntity(request, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE);
        }

    }
}
