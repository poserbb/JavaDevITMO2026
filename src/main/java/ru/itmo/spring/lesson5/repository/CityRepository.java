package ru.itmo.spring.lesson5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itmo.spring.lesson5.model.City;
import ru.itmo.spring.lesson5.model.Region;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCode(int code);

    boolean existsByCode(int code);

    List<City> findAllByRegion(Region region);

    @Query("SELECT c FROM City c JOIN FETCH c.region WHERE c.id = :id")
    Optional<City> findByIdWithRegion(Long id);
}
