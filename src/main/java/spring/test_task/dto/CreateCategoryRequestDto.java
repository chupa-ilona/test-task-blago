package spring.test_task.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Type cannot be blank")
    private String type;
}
