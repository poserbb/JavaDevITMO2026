package ru.itmo.spring.lesson6.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.spring.lesson6.dto.RegionRequestDto;
import ru.itmo.spring.lesson6.dto.RegionResponseDto;
import ru.itmo.spring.lesson6.model.Region;
import ru.itmo.spring.lesson6.repository.CityRepository;
import ru.itmo.spring.lesson6.repository.RegionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionService {

    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;

    public List<RegionResponseDto> findAllRegions() {
        return regionRepository.findAll().stream()
                .map(RegionResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public RegionResponseDto findRegionById(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Регион с ID " + id + " не найден"));
        return RegionResponseDto.fromEntity(region);
    }

    public RegionResponseDto findRegionByCode(Integer code) {
        Region region = regionRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Регион с кодом " + code + " не найден"));
        return RegionResponseDto.fromEntity(region);
    }

    @Transactional
    public RegionResponseDto createRegion(RegionRequestDto request) {

        Region region = Region.builder()
                .code(request.getCode())
                .name(request.getName())
                .build();

        Region saved = regionRepository.save(region);
        return RegionResponseDto.fromEntity(saved);
    }

    @Transactional
    public RegionResponseDto updateRegion(Long id, RegionRequestDto request) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Регион с ID " + id + " не найден"));


        region.setCode(request.getCode());
        region.setName(request.getName());

        Region updated = regionRepository.save(region);
        return RegionResponseDto.fromEntity(updated);
    }

    @Transactional
    public void deleteRegion(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Регион с ID " + id + " не найден"));

        regionRepository.delete(region);
    }
}
