package ru.itmo.spring.lesson6.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.spring.lesson6.dto.CityRequestDto;
import ru.itmo.spring.lesson6.dto.CityResponseDto;
import ru.itmo.spring.lesson6.service.CityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
@Tag(name = "Города", description = "CRUD операции для управления городами")
public class CityController {

    private final CityService cityService;

    @Operation(summary = "Получить все города", description = "Возвращает список всех городов с информацией о регионах")
    @GetMapping
    public ResponseEntity<List<CityResponseDto>> getAllCities() {
        return ResponseEntity.ok(cityService.findAllCities());
    }

    @Operation(summary = "Получить город по ID", description = "Возвращает информацию о городе по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город найден"),
            @ApiResponse(responseCode = "404", description = "Город не найден", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDto> getCityById(
            @Parameter(description = "ID города", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(cityService.findCityById(id));
    }


    @Operation(summary = "Получить город по коду", description = "Возвращает информацию о городе по коду")
    @GetMapping("/code/{code}")
    public ResponseEntity<CityResponseDto> getCityByCode(
            @Parameter(description = "Код города", example = "495")
            @PathVariable Integer code) {
        return ResponseEntity.ok(cityService.findCityByCode(code));
    }

    @Operation(summary = "Получить города по региону", description = "Возвращает список городов по коду региона")
    @GetMapping("/region/{regionCode}")
    public ResponseEntity<List<CityResponseDto>> getCitiesByRegionCode(
            @Parameter(description = "Код региона", example = "77")
            @PathVariable Integer regionCode) {
        return ResponseEntity.ok(cityService.findCitiesByRegionCode(regionCode));
    }

    @Operation(summary = "Поиск городов по названию", description = "Ищет города по части названия (без учета регистра)")
    @GetMapping("/search")
    public ResponseEntity<List<CityResponseDto>> searchCities(
            @Parameter(description = "Часть названия города", example = "Моск")
            @RequestParam String name) {
        return ResponseEntity.ok(cityService.searchCitiesByName(name));
    }


    @Operation(summary = "Создать город", description = "Создает новый город")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Город создан"),
            @ApiResponse(responseCode = "400", description = "Неверные данные запроса", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CityResponseDto> createCity(@Valid @RequestBody CityRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.createCity(request));
    }

    @Operation(summary = "Обновить город", description = "Обновляет информацию о существующем городе")
    @PutMapping("/{id}")
    public ResponseEntity<CityResponseDto> updateCity(
            @Parameter(description = "ID города", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody CityRequestDto request) {
        return ResponseEntity.ok(cityService.updateCity(id, request));
    }

    @Operation(summary = "Удалить город", description = "Удаляет город по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(
            @Parameter(description = "ID города", example = "1")
            @PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
