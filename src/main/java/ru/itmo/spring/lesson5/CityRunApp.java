package ru.itmo.spring.lesson5;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.support.TransactionTemplate;
import ru.itmo.spring.lesson5.service.CityService;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class CityRunApp implements CommandLineRunner {

    private final CityService cityService;
    private final Scanner scanner = new Scanner(System.in);
    private final TransactionTemplate transactionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(CityRunApp.class, args);
    }

    @Override
    public void run(String... args) {

        System.out.println("        СПРАВОЧНИК ГОРОДОВ");


        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {

                case "1" -> transactionTemplate.executeWithoutResult(status -> cityService.addCity());
                case "2" -> cityService.viewAllCities();
                case "3" -> cityService.findByCode();
                case "4" -> transactionTemplate.executeWithoutResult(status -> cityService.updateCity());
                case "5" -> transactionTemplate.executeWithoutResult(status -> cityService.deleteCity());


                case "6" -> cityService.viewAllRegions();
                case "7" -> transactionTemplate.executeWithoutResult(status -> cityService.addRegion());
                case "8" ->
                        transactionTemplate.executeWithoutResult(status -> cityService.deleteRegion()); // Удаление по коду

                case "0" -> {
                    System.out.println("\nВыход из программы");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }

            System.out.println("\nНажмите Enter чтобы продолжить");
            scanner.nextLine();
        }
    }

    private void printMenu() {
        System.out.println("\n" + "─".repeat(55));
        System.out.println("ГЛАВНОЕ МЕНЮ");
        System.out.println("─".repeat(55));
        System.out.println("【ГОРОДА】");
        System.out.println("  1. Добавить город");
        System.out.println("  2. Показать все города");
        System.out.println("  3. Найти город по коду");
        System.out.println("  4. Обновить город");
        System.out.println("  5. Удалить город");
        System.out.println("\n【РЕГИОНЫ】");
        System.out.println("  6. Показать все регионы");
        System.out.println("  7. Добавить регион");
        System.out.println("  8. Удалить регион по коду");
        System.out.println("\n  0. Выход");
        System.out.println("─".repeat(55));
        System.out.print("Выберите действие: ");
    }
}
