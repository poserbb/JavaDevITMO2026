package ru.itmo.spring.lesson4;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CityDaoImpl implements CityDao {
    private static final RowMapper<City> CITIES_ROW_MAPPER = (rs, rowNum) -> {
        City city = new City();
        city.setId(rs.getLong("id"));
        city.setCode(rs.getInt("code"));
        city.setNameRu(rs.getString("name_ru"));
        city.setNameEn(rs.getString("name_en"));
        city.setPopulation(rs.getInt("population"));
        return city;

    };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAll() {
        String sql = """
                SELECT id, code, name_ru, name_en, population 
                FROM cities 
                ORDER BY id
                """;

        return jdbcTemplate.query(sql, CITIES_ROW_MAPPER);
    }

    @Override
    public Optional<City> findById(Integer id) {
        String sql = """
                SELECT id, code, name_ru, name_en, population 
                FROM cities 
                WHERE id = :id
                """;

        return jdbcTemplate.query(sql, Map.of("id", id), CITIES_ROW_MAPPER).stream().findFirst();
    }

    @Override
    public Optional<City> findByCode(int code) {
        String sql = """
                    SELECT id, code, name_ru, name_en, population 
                    FROM cities 
                    WHERE code = :code
                """;

        return jdbcTemplate.query(sql, Map.of("code", code), CITIES_ROW_MAPPER).stream().findFirst();
    }

    @Override
    public void save(City city) {
        String sql = """
                    INSERT INTO cities (code, name_ru, name_en, population) 
                    VALUES (:code, :nameRu, :nameEn, :population)
                """;

        Map<String, Object> params = Map.of("code", city.getCode(), "nameRu", city.getNameRu(), "nameEn", city.getNameEn(), "population", city.getPopulation());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void update(City city) {
        String sql = """
                    UPDATE cities
                    SET code = :code,
                    name_ru = :nameRu,
                    name_en = :nameEn, 
                    population = :population
                    WHERE id = :id
                """;

        Map<String, Object> params = Map.of("id", city.getId(), "code", city.getCode(), "nameRu", city.getNameRu(), "nameEn", city.getNameEn(), "population", city.getPopulation());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM cities WHERE id = :id";
        jdbcTemplate.update(sql, Map.of("id", id));
    }
}




