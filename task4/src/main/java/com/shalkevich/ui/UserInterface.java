package com.shalkevich.ui;

import com.shalkevich.function.FunctionFactory;
import com.shalkevich.service.CalculationService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * Отвечает за взаимодействие с пользователем.
 */
@Slf4j
public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Запускает взаимодействие с пользователем для выбора функции и ввода значения N.
     */
    public void start() {
        log.info("Выберите функцию для вычисления:");
        log.info("1. \\sum_{i=0}^{\\infty} \\frac{1}{2^i}");
        log.info("2. \\sum_{n=1}^{\\infty} \\frac{1}{n^2}");
        log.info("3. \\sum_{n=1}^{\\infty} \\frac{1}{n(n+1)}");
        log.info("4. \\sum_{n=1}^{\\infty} \\frac{1}{n}");

        int functionChoice = scanner.nextInt();
        log.info("Введите значение N: ");

        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            log.info("Начало вычислений для N = {}, используя функцию {}", n, functionChoice);

            CalculationService calculationService = new CalculationService();
            double result = calculationService.calculateSum(n, FunctionFactory.getFunctionStrategy(functionChoice));

            log.info("Сумма значений: {}", result);
        } else {
            log.error("Введено некорректное значение. Пожалуйста, введите целое число.");
        }
    }

    /**
     * Закрывает Scanner.
     */
    public void close() {
        scanner.close();
    }
}
