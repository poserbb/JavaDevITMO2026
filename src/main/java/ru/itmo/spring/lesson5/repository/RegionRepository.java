package ru.itmo.spring.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.spring.lesson5.model.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByCode(Integer code);

    Optional<Region> findByName(String name);

    boolean existsByCode(Integer code);

    boolean existsByName(String name);
}
