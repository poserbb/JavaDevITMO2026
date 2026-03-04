package ru.itmo.spring.lesson3;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class FibonachiFinder implements FibFinderInterface{
    private static Map<Integer, Long> cache = new HashMap<>();

    static {
        cache.put(0, 0L);
        cache.put(1, 1L);
    }

    @Override
    public long finder(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        long calculate = finder(n - 1) + finder(n - 2);
        cache.put(n, calculate);
        return calculate;
    }
}
