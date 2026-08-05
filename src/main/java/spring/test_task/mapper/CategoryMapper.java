package spring.test_task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import spring.test_task.dto.CategoryDto;
import spring.test_task.dto.CreateCategoryRequestDto;
import spring.test_task.entity.Category;

@Mapper(config = spring.test_task.config.MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequestDto request);

    void updateEntity(CreateCategoryRequestDto request,
                      @MappingTarget Category category);

}
