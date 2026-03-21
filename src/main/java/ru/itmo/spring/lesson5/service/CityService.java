package ru.itmo.spring.lesson5.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.spring.lesson5.model.City;
import ru.itmo.spring.lesson5.model.Region;
import ru.itmo.spring.lesson5.repository.CityRepository;
import ru.itmo.spring.lesson5.repository.RegionRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Transactional
public class CityService {
    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;
    private final Scanner scanner = new Scanner(System.in);

    @Transactional
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

        System.out.print("Введите код региона: ");
        Integer regionCode = Integer.parseInt(scanner.nextLine());

        Optional<Region> regionOpt = regionRepository.findByCode(regionCode);
        if (regionOpt.isEmpty()) {
            System.out.println("Регион с кодом " + regionCode + " не найден!");
            return;
        }
        Region region = regionOpt.get();


        City city = City.builder()
                .code(code)
                .nameRu(nameRu)
                .nameEn(nameEn)
                .population(population)
                .region(region)
                .build();
        City savedCity = cityRepository.save(city);
        System.out.println("Город добавлен! ID: " + city.getId() + "  Регион: " + region.getName());
    }

    public void viewAllCities() {
        System.out.println("\n--- Список всех городов ---");
        List<City> cities = cityRepository.findAll();

        if (cities.isEmpty()) {
            System.out.println("Список городов пуст");
            return;
        }

        for (City city : cities) {
            System.out.printf("│ %-2d │ %-6s │ %-16s │ %-16s │ %-8d │ %-6s │%n",
                    city.getId(), city.getCode(), city.getNameRu(),
                    city.getNameEn(), city.getPopulation(), city.getRegion());
        }

    }

    public void findByCode() {
        System.out.print("\nВведите код города: ");
        int code = scanner.nextInt();
        scanner.nextLine();

        Optional<City> cityOpt = cityRepository.findByCode(code);
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


    @Transactional
    public void updateCity() {
        System.out.print("\nВведите ID города для обновления: ");
        Long id = Long.parseLong(scanner.nextLine());


        Optional<City> cityOpt = cityRepository.findById(id);
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
        System.out.println("Новый регион (Enter - оставить " + city.getRegion() + "): ");
        int newRegion = Integer.parseInt(scanner.nextLine());

        Optional<Region> newRegionOpt = regionRepository.findByCode(newRegion);
        if (newRegionOpt.isPresent()) {
            city.setRegion(newRegionOpt.get());
            System.out.println("Регион изменен на: " + newRegionOpt.get().getName() +
                    " (код: " + newRegionOpt.get().getCode() + ")");
        } else {
            System.out.println("Регион с кодом " + newRegion + " не найден, регион не изменен");
        }

        cityRepository.save(city);
        System.out.println("Город обновлен!");
    }


    @Transactional
    public void deleteCity() {
        System.out.print("\nВведите ID города для удаления: ");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty()) {
            System.out.println("Город с ID " + id + " не найден");
            return;
        }

        City city = cityOpt.get();
        System.out.println("Вы уверены, что хотите удалить город " + city.getNameRu() + "? (да/нет)");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("да")) {
            cityRepository.deleteById(id);
            System.out.println("Город удален!");
        } else {
            System.out.println("Удаление отменено");
        }
    }

    public void viewAllRegions() {
        System.out.println("\nСПИСОК ВСЕХ РЕГИОНОВ");
        List<Region> regions = regionRepository.findAll();

        if (regions.isEmpty()) {
            System.out.println("Список регионов пуст");
        }

        for (Region region : regions) {
            System.out.printf("│ %-2d │ %-6s │ %-16s │%n",
                    region.getId(), region.getCode(), region.getName());
        }
    }

    @Transactional
    public void addRegion() {
        System.out.println("\nДОБАВЛЕНИЕ НОВОГО РЕГИОНА");

        System.out.print("Введите код региона: ");
        Integer code = Integer.parseInt(scanner.nextLine());


        System.out.print("Введите название региона: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("название не может быть пустым");
            return;
        }


        Region region = Region.builder()
                .code(code)
                .name(name)
                .build();

        regionRepository.save(region);
        System.out.println("Регион добавлен!");
    }

    @Transactional
    public void deleteRegion() {
        System.out.println("\nУДАЛЕНИЕ РЕГИОНА");

        System.out.print("Введите код региона для удаления: ");
        Integer regionCode = Integer.parseInt(scanner.nextLine());


        Optional<Region> regionOpt = regionRepository.findByCode(regionCode);
        if (regionOpt.isEmpty()) {
            System.out.println("Регион с кодом " + regionCode + " не найден");
            return;
        }
        Region region = regionOpt.get();

        System.out.print("Вы уверены, что хотите удалить регион \"" + region.getName() + "\"? (да/нет): ");
        if (scanner.nextLine().equalsIgnoreCase("да")) {
            regionRepository.delete(region);
            System.out.println("✓ Регион удален!");
        }
    }

}
