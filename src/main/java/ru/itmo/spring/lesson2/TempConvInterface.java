package ru.itmo.spring.lesson2;

import org.springframework.stereotype.Component;


public interface TempConvInterface  {

    double cToK(double c);
    double cToF(double c);
    double kToC(double k);
    double kToF(double k);
    double fToC(double f);
    double fToK(double f);
}
