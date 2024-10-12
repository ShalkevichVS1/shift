package com.shalkevich.projectLogic;

import com.shalkevich.interfaces.Table;

/**
 * A class for creating and displaying multiplication tables.
 */

public class MultiplicationTable implements Table {

    private final int size;
    private final int cellWidth;

    /**
     * Constructor for creating a multiplication table of a given size.
     * @param size table size.
     */
    public MultiplicationTable(int size) {
        this.size = size;
        int maxNum = size * size;
        this.cellWidth = String.valueOf(maxNum).length();
    }

    /**
     * Method for printing multiplication tables.
     */
    @Override
    public void print() {
        printHeader();
        printHorizontalDivider();
        for (int i = 1; i <= size; i++) {
            printRow(i);
            printHorizontalDivider();
        }
    }

    /**
     * Method to print table header.
     */
    private void printHeader() {
        System.out.printf("%-" + cellWidth + "s|", "");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%" + cellWidth + "d|", i);
        }
        System.out.println();
    }

    /**
     * Method for printing horizontal separator.
     */

    private void printHorizontalDivider() {
        for (int i = 0; i <= size; i++) {
            System.out.print("-".repeat(cellWidth) + "+");
        }
        System.out.println();
    }

    /**
     * Method for printing multiplication table row.
     * @param rowNumber line number.
     */

    private void printRow(int rowNumber) {
        System.out.printf("%" + cellWidth + "d|", rowNumber);
        for (int j = 1; j <= size; j++) {
            System.out.printf("%" + cellWidth + "d|", rowNumber * j);
        }
        System.out.println();
    }
}

