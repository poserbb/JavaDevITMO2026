package ru.itmo.spring.lesson6.dto;

import lombok.Builder;
import lombok.Data;
import ru.itmo.spring.lesson6.model.Region;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@Schema(description = "Ответ с информацией о регионе")
public class RegionResponseDto {

    @Schema(description = "ID региона", example = "1")
    private Long id;

    @Schema(description = "Код региона", example = "77")
    private Integer code;

    @Schema(description = "Название региона", example = "Москва")
    private String name;

    public static RegionResponseDto fromEntity(Region region) {
        return RegionResponseDto.builder()
                .id(region.getId())
                .code(region.getCode())
                .name(region.getName())
                .build();
    }
}
