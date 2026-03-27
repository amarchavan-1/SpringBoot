package com.example.serviceexample.service;

import org.springframework.stereotype.Service;

@Service
public class FactorialService {
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed.");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
