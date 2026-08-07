package spring.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.test_task.dto.CategoryDto;
import spring.test_task.dto.CreateCategoryRequestDto;
import spring.test_task.service.CategoryService;
import java.util.List;

@Tag(name = "Category management",
        description = "Endpoints for managing transaction categories")
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new category",
            description = "Creates a new category for transactions. Name must be unique.")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto request) {
        return categoryService.createCategory(request);
    }

    @GetMapping
    @Operation(summary = "Get all categories",
            description = "Retrieves a list of all available categories.")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID",
            description = "Retrieves a specific category by its unique ID.")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category",
            description = "Updates the name and type of an existing category by its ID.")
    public CategoryDto updateCategory(@PathVariable Long id,
                                      @RequestBody @Valid CreateCategoryRequestDto request) {
        return categoryService.updateCategory(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category",
            description = "Soft deletes a category by its ID.")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
