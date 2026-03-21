package ru.itmo.spring.lesson5.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
@Builder
public class City implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private int code;

    @Column(name = "nameRu", nullable = false)
    private String nameRu;

    @Column(name = "nameEn")
    private String nameEn;

    @Column(name = "population")
    private Integer population;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Builder
    public City(int code, String nameRu, String nameEn, Integer population) {
        this.code = code;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.population = population;
        this.region = region;
    }

    @Override
    public String toString() {
        return String.format("Код %s \n" +
                        "Название: %s \n" +
                        "название на английском: %s \n" +
                        "Население: %d",
                code, nameRu, nameEn, population);
    }


}
