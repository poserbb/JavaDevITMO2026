package ru.itmo.spring.lesson6.dto;

import lombok.Builder;
import lombok.Data;
import ru.itmo.spring.lesson6.model.City;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@Schema(description = "Ответ с информацией о городе")
public class CityResponseDto {

    @Schema(description = "ID города", example = "1")
    private Long id;

    @Schema(description = "Код города", example = "495")
    private Integer code;

    @Schema(description = "Название на русском", example = "Москва")
    private String nameRu;

    @Schema(description = "Название на английском", example = "Moscow")
    private String nameEn;

    @Schema(description = "Численность населения", example = "13010112")
    private Integer population;

    @Schema(description = "Информация о регионе")
    private RegionInfo region;

    @Data
    @Builder
    @Schema(description = "Информация о регионе")
    public static class RegionInfo {
        @Schema(description = "ID региона", example = "1")
        private Long id;

        @Schema(description = "Код региона", example = "77")
        private Integer code;

        @Schema(description = "Название региона", example = "Москва")
        private String name;
    }

    public static CityResponseDto fromEntity(City city) {
        return CityResponseDto.builder()
                .id(city.getId())
                .code(city.getCode())
                .nameRu(city.getNameRu())
                .nameEn(city.getNameEn())
                .population(city.getPopulation())
                .region(RegionInfo.builder()
                        .id(city.getRegion().getId())
                        .code(city.getRegion().getCode())
                        .name(city.getRegion().getName())
                        .build())
                .build();
    }
}
