package org.zaradev.testing;

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
}
