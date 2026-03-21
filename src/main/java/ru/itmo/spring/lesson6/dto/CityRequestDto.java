package ru.itmo.spring.lesson6.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Запрос на создание")
public class CityRequestDto {

    @Schema(description = "Код города (телефонный)", example = "495")
    @NotNull(message = "Код города обязателен")
    @Positive(message = "Код города не может быть отрицательным")
    private Integer code;

    @Schema(description = "Название города на русском", example = "Москва")
    @NotBlank(message = "Название на русском обязательно")
    private String nameRu;

    @Schema(description = "Название города на английском", example = "Moscow")
    private String nameEn;

    @Schema(description = "Численность населения", example = "13010112")
    @PositiveOrZero(message = "Население не может быть отрицательным")
    private Integer population;

    @Schema(description = "Код региона", example = "77")
    @NotNull(message = "Код региона обязателен")
    @Positive(message = "Код региона не может быть отрицательным")
    private Integer regionCode;
}