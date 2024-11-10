package com.shalkevich.service;

import java.util.Scanner;

/**
 * Working with user input.
 */
public class UserInput {

    /**
     * Prompts the user for table size.
     *
     * @return table size.
     */
    public int getTableSize() {
        int size;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Введите размер таблицы (от 1 до 32): ");
                if (scanner.hasNextInt()) {
                    size = scanner.nextInt();
                    if (size >= 1 && size <= 32) {
                        return size;
                    }
                    System.out.println("Пожалуйста, введите число от 1 до 32.");
                } else {
                    System.out.println("Пожалуйста, введите корректное число.");
                    scanner.next();
                }
            }
        }
    }
}

