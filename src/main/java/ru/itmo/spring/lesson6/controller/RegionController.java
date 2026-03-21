package ru.itmo.spring.lesson6.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.spring.lesson6.dto.RegionRequestDto;
import ru.itmo.spring.lesson6.dto.RegionResponseDto;
import ru.itmo.spring.lesson6.service.RegionService;
import jakarta.validation.constraints.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
@Tag(name = "Регионы", description = "CRUD операции для управления регионами")
public class RegionController {

    private final RegionService regionService;

    @Operation(summary = "Получить все регионы", description = "Возвращает список всех регионов")
    @GetMapping
    public ResponseEntity<List<RegionResponseDto>> getAllRegions() {
        return ResponseEntity.ok(regionService.findAllRegions());
    }

    @Operation(summary = "Получить регион по ID", description = "Возвращает информацию о регионе по ID")
    @GetMapping("/{id}")
    public ResponseEntity<RegionResponseDto> getRegionById(
            @Parameter(description = "ID региона", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(regionService.findRegionById(id));
    }

    @Operation(summary = "Получить регион по коду", description = "Возвращает информацию о регионе по его коду")
    @GetMapping("/code/{code}")
    public ResponseEntity<RegionResponseDto> getRegionByCode(
            @Parameter(description = "Код региона", example = "77")
            @PathVariable Integer code) {
        return ResponseEntity.ok(regionService.findRegionByCode(code));
    }

    @Operation(summary = "Создать регион", description = "Создает новый регион")
    @PostMapping
    public ResponseEntity<RegionResponseDto> createRegion(@Valid @RequestBody RegionRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.createRegion(request));
    }

    @Operation(summary = "Обновить регион", description = "Обновляет информацию о существующем регионе")
    @PutMapping("/{id}")
    public ResponseEntity<RegionResponseDto> updateRegion(
            @Parameter(description = "ID региона", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody RegionRequestDto request) {
        return ResponseEntity.ok(regionService.updateRegion(id, request));
    }

    @Operation(summary = "Удалить регион", description = "Удаляет регион по ID (только если нет городов)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(
            @Parameter(description = "ID региона", example = "1")
            @PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
