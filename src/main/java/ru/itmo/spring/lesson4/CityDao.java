package ru.itmo.spring.lesson4;


import java.util.List;
import java.util.Optional;

public interface CityDao {

    List<City> findAll();

    Optional<City> findById(Integer id);

    Optional<City> findByCode(int code);

    void save(City cities);

    void update(City cities);

    void delete(Integer id);


}
