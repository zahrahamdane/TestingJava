package org.zaradev.testing;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiplay(int a, int b) {
        return a * b;
    }

    public void longCalculator () {
        try {
            // Attendre 2 secondes
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> digitsSet(int number) {
        Set<Integer> integers = new HashSet<>();
        String numberString = String.valueOf(number);

        for(int i =0; i<numberString.length(); i++) {
            if(numberString.charAt(i) != '-') {
                integers.add(Integer.parseInt(numberString, i, i+1, 10));
            }

        }

        return integers;
    }
}
