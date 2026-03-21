package ru.itmo.spring.lesson6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.spring.lesson6.model.City;
import ru.itmo.spring.lesson6.model.Region;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        cities.add(city);
        city.setRegion(null);
    }

    public void removeCity(City city) {
        cities.remove(city);
        city.setRegion(null);
    }

    @Override
    public String toString() {
        return "Region{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
