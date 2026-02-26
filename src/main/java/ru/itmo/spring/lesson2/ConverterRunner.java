package ru.itmo.spring.lesson2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class ConverterRunner {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConverterRunner.class, args);

        TemperatureConverter tempConv = context.getBean(TemperatureConverter.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("введите температуру в цельсиях" + "\n");
        double c = scanner.nextDouble();
        System.out.println(c + "°C = " + tempConv.cToF(c) + "°K");
        System.out.println(c + "°C = " + tempConv.cToF(c) + "°F");

        System.out.println("введите температуру в кельвинах" + "\n");
        double k = scanner.nextDouble();
        System.out.println(k + "K = " + tempConv.kToC(k) + "°C");
        System.out.println(k + "K = " + tempConv.kToF(k) + "°F");

        System.out.println("введите температуру в фаренгейтах" + "\n");
        double f = scanner.nextDouble();
        System.out.println(f + "°F = " + tempConv.fToC(f) + "°C");
        System.out.println(f + "°F = " + tempConv.fToK(f) + "°K");
    }
}
