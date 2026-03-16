package ru.itmo.spring.lesson4;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {


    @Setter
    private Long id;
    private int code;
    private String nameRu;
    private String nameEn;
    private Integer population;

    public City(int code, String nameRu, String nameEn, Integer population) {
        this.code = code;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.population = population;
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
