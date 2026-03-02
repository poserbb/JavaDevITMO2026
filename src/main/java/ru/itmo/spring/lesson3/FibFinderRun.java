package ru.itmo.spring.lesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.Scanner;

@SpringBootApplication
public class FibFinderRun {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FibFinderRun.class, args);
        FibFinderInterface fibFind = context.getBean(FibFinderInterface.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Это поиск элементов в числе Фибоначчи" + "\nВведите номер элемента");
        System.out.println(fibFind.finder(scanner.nextInt()));

    }
}
