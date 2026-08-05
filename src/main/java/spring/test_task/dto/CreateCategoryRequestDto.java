package spring.test_task.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255)
    private String name;

    @NotNull(message = "Type cannot be blank")
    @Pattern(regexp = "^(INCOME|EXPENSE)$", message = "Type must be INCOME or EXPENSE")
    private String type;
}
