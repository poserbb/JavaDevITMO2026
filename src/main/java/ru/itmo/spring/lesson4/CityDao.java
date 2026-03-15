package ru.itmo.spring.lesson4;



import java.util.List;
import java.util.Optional;

public interface CityDao {

    List<Cities> findAll();
    Optional<Cities> findById(Integer id);
    Optional<Cities> findByCode(int code);
    void save(Cities cities);
    void update(Cities cities);
    void delete(Integer id);




}
