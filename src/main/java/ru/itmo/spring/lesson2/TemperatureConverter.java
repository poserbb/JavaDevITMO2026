package ru.itmo.spring.lesson2;
import org.springframework.stereotype.Component;

@Component
public class TemperatureConverter {
    private static final TemperatureConverter tempConv = new TemperatureConverter();

    private TemperatureConverter() {

    }

    public double cToK(double c) {
        return c + 273.15;
    }

    public double cToF(double c) {
        return c * 9/5 + 32;
    }

    public double kToC(double k) {
        return k - 273.15;
    }

    public double kToF(double k) {
        return (k - 273.15) * 9/5 + 32;
    }

    public double fToC(double f) {
        return (f - 32) * 5/9;
    }

    public double fToK(double f) {
        return (f - 32) * 5/9 + 273.15;
    }


}
