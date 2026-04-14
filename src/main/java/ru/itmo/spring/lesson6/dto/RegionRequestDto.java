package ru.itmo.spring.lesson6.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Запрос на создание")
public class RegionRequestDto {

    @Schema(description = "Код региона", example = "77")
    @NotNull(message = "Код региона обязателен")
    @Positive(message = "Код региона должен быть положительным")
    private Integer code;

    @Schema(description = "Название региона", example = "Москва")
    @NotBlank(message = "Название региона обязательно")
    private String name;
}
