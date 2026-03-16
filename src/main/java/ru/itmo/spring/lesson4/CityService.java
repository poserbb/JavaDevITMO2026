package ru.itmo.spring.lesson4;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityDao cityDao;
    private final Scanner scanner = new Scanner(System.in);

    public void addCity() {
        System.out.println("\n Добавление нового города");

        System.out.print("Введите код города: ");
        int code = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите название на русском: ");
        String nameRu = scanner.nextLine().trim();

        System.out.print("Введите название на английском: ");
        String nameEn = scanner.nextLine().trim();

        System.out.print("Введите численность населения: ");
        int population = Integer.parseInt(scanner.nextLine());

        City city = new City(code, nameRu, nameEn, population);
        cityDao.save(city);
        System.out.println("Город добавлен! ID: " + city.getId());
    }

    public void viewAllCities() {
        System.out.println("\n--- Список всех городов ---");
        List<City> cities = cityDao.findAll();

        if (cities.isEmpty()) {
            System.out.println("Список городов пуст");
            return;
        }

        for (City city : cities) {
            System.out.printf("│ %-2d │ %-6s │ %-16s │ %-16s │ %-8d │%n",
                    city.getId(), city.getCode(), city.getNameRu(),
                    city.getNameEn(), city.getPopulation());
        }

    }

    public void findByCode() {
        System.out.print("\nВведите код города: ");
        int code = scanner.nextInt();

        Optional<City> cityOpt = cityDao.findByCode(code);
        if (cityOpt.isPresent()) {
            City city = cityOpt.get();
            System.out.println("Город найден:");
            System.out.printf("ID: %d, Код: %s, %s (%s), Население: %d%n",
                    city.getId(), city.getCode(), city.getNameRu(),
                    city.getNameEn(), city.getPopulation());
        } else {
            System.out.println("Город с кодом " + code + " не найден");
        }
    }

    public void updateCity() {
        System.out.print("\nВведите ID города для обновления: ");
        int id = Integer.parseInt(scanner.nextLine());


        Optional<City> cityOpt = cityDao.findById(id);
        if (cityOpt.isEmpty()) {
            System.out.println("Город с ID " + id + " не найден");
            return;
        }

        City city = cityOpt.get();
        System.out.println("Текущая информация: " + city.getNameRu() + " (" + city.getCode() + ")");

        System.out.print("Новый код (Enter - оставить '" + city.getCode() + "'): ");
        int codeNew = scanner.nextInt();
        city.setCode(codeNew);

        System.out.print("Новое русское название (Enter - оставить '" + city.getNameRu() + "'): ");
        String nameRu = scanner.nextLine();
        if (!nameRu.isEmpty()) city.setNameRu(nameRu);

        System.out.print("Новое английское название (Enter - оставить '" + city.getNameEn() + "'): ");
        String nameEn = scanner.nextLine();
        if (!nameEn.isEmpty()) city.setNameEn(nameEn);

        System.out.print("Новое население (Enter - оставить " + city.getPopulation() + "): ");
        String populationStr = scanner.nextLine();
        if (!populationStr.isEmpty()) {
            city.setPopulation(Integer.parseInt(populationStr));
        }

        cityDao.update(city);
        System.out.println("Город обновлен!");
    }

    public void deleteCity() {
        System.out.print("\nВведите ID города для удаления: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<City> cityOpt = cityDao.findById(id);
        if (cityOpt.isEmpty()) {
            System.out.println("Город с ID " + id + " не найден");
            return;
        }

        City city = cityOpt.get();
        System.out.println("Вы уверены, что хотите удалить город " + city.getNameRu() + "? (да/нет)");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("да")) {
            cityDao.delete(id);
            System.out.println("Город удален!");
        } else {
            System.out.println("Удаление отменено");
        }
    }

}
