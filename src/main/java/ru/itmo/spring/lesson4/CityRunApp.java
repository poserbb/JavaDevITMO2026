package ru.itmo.spring.lesson4;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class CityRunApp implements CommandLineRunner {

    private final CityService cityService;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(CityRunApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("СПРАВОЧНИК ГОРОДОВ");

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> cityService.addCity();
                case "2" -> cityService.viewAllCities();
                case "3" -> cityService.findByCode();
                case "4" -> cityService.updateCity();
                case "5" -> cityService.deleteCity();
                case "0" -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }

            System.out.println("\nНажмите Enter чтобы продолжить");
            scanner.nextLine();
        }
    }

    private void printMenu() {
        System.out.println("\n");
        System.out.println("---МЕНЮ---");
        System.out.println("1. Добавить город ");
        System.out.println("2. Показать все города ");
        System.out.println("3. Найти город по коду");
        System.out.println("4. Обновить город ");
        System.out.println("5. Удалить город ");
        System.out.println("0. Выход ");
        System.out.print("Выберите действие: ");
    }
}
