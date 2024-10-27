package org.zaradev.testing;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculatorUnderTest;
    private static Instant startedAt;

    @BeforeAll
    public static void initStartingTime() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showTestDuration() {
        System.out.println("Appel apres tous les tests");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @BeforeEach
    public void initCalculator() {
        calculatorUnderTest = new Calculator();
        System.out.println("Appel avant chaque test");
    }

    @AfterEach
    public void undefCalculator() {
        System.out.println("Appel apres chaque test");
        calculatorUnderTest = null;
    }

    @Test
    void testAddTwoPositiveNumbers() {
        // Arrange
        int a = 2;
        int b = 3;

        // Act
        int somme = calculatorUnderTest.add(a, b);

        // Assert
        assertThat(somme).isEqualTo(5);
    }

    @Test
    void multiply_shouldReturnTheProduct_ofTwoIntegers() {
        // Arrange
        int a = 42;
        int b = 11;

        // Act
        int result = calculatorUnderTest.multiplay(a, b);

        // Assert
        assertThat(result).isEqualTo(462);
    }

    @ParameterizedTest(name = "{0} x 0 doit être égal à 0")
    @ValueSource(ints = { 1, 2, 42, 1001, 5089 })
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
        // Arrange -- Tout est prêt !

        // Act -- Multiplier par zéro
        final int actualResult = calculatorUnderTest.multiplay(arg, 0);

        // Assert -- ça vaut toujours zéro !
        assertThat(actualResult).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} + {1} should equal to {2}")
    @CsvSource({"1,1,2", "2,3,5", "42,57,99"})
    public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
        // Arrange -- Tout est prêt !

        // Act
        int actualResult = calculatorUnderTest.add(arg1, arg2);

        // Assert
        assertThat(actualResult).isEqualTo(expectResult);
    }

    @Timeout(3)
    @Test
    public void longCalcul_shouldComputeInLessThan1Second() {
        // Arrange

        // Act
        calculatorUnderTest.longCalculator();

        //Assert

    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger () {
        // GIVEN
        final int number = 95897;

        // WHEN
        final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        // final Set<Integer> expectDigits = Stream.of(5,7,8,9).collect(Collectors.toSet());
        assertThat(actualDigits).containsExactlyInAnyOrder(5,7,8,9);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger () {
        // GIVEN
        final int number = -124432;

        // WHEN
        final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        // final Set<Integer> expectDigits = Stream.of(1,2,3,4).collect(Collectors.toSet());

        assertThat(actualDigits).containsExactlyInAnyOrder(1,2,3,4);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfZero_ofZero () {
        // GIVEN
        final int number = 0;

        // WHEN
        final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        // final Set<Integer> expectDigits = Stream.of(0).collect(Collectors.toSet());

        assertThat(actualDigits).containsExactly(0);
    }

}
