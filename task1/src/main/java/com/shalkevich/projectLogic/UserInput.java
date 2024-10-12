package com.shalkevich.projectLogic;

import java.util.Scanner;

/**
 * Class for working with user input.
 */
public class UserInput {

    /**
     * Prompts the user for table size.
     * @return table size.
     */
    public int getTableSize() {
        Scanner scanner = new Scanner(System.in);
        int size;
        while (true) {
            System.out.print("Введите размер таблицы (от 1 до 32): ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size >= 1 && size <= 32) {
                    break;
                } else {
                    System.out.println("Пожалуйста, введите число от 1 до 32.");
                }
            } else {
                System.out.println("Пожалуйста, введите корректное число.");
                scanner.next();
            }
        }
        return size;
    }
}
