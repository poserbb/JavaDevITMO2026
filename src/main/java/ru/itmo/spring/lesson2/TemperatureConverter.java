package ru.itmo.spring.lesson2;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import static ru.itmo.spring.lesson2.Constants.*;

@Component
@NoArgsConstructor
public class TemperatureConverter implements TempConvInterface {

    @Override
    public double cToK(double c) {
        return c + kelConst;
    }

    @Override
    public double cToF(double c) {
        return (c * nineFive) + farConst;
    }

    @Override
    public double kToC(double k) {
        return k - kelConst;
    }

    @Override
    public double kToF(double k) {
        return (k - kelConst) * nineFive + farConst;
    }

    @Override
    public double fToC(double f) {
        return (f - farConst) * fiveNine;
    }

    @Override
    public double fToK(double f) {
        return (f - 32) * 5/9 + 273.15;
    }


}
