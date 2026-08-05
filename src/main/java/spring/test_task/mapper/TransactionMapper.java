package spring.test_task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.test_task.dto.CreateTransactionRequestDto;
import spring.test_task.dto.TransactionDto;
import spring.test_task.entity.Category;
import spring.test_task.entity.Transaction;

@Mapper(config = spring.test_task.config.MapperConfig.class)
public interface TransactionMapper {
    TransactionDto toDto(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "category", source = "category")
    Transaction toEntity(CreateTransactionRequestDto request, Category category);

    Transaction toEntity(TransactionDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "category", source = "category")
    void updateEntity(CreateTransactionRequestDto request, Category category, @MappingTarget Transaction transaction);

}
