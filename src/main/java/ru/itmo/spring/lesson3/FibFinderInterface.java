package ru.itmo.spring.lesson3;

import org.springframework.stereotype.Component;

@Component
public interface FibFinderInterface {
      long finder(int n);
}
