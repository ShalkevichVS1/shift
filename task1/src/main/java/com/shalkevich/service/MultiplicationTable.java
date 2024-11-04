package com.shalkevich.service;

/**
 * Creating and displaying multiplication tables.
 */
public class MultiplicationTable implements Table {

    private static final String CELL_SEPARATOR = "d|";

    private final int size;
    private final int cellWidth;
    private final int headerWidth;
    private final String horizontalDivider;

    public MultiplicationTable(int size) {
        this.size = size;
        int maxNum = size * size;
        this.cellWidth = String.valueOf(maxNum).length();
        this.headerWidth = String.valueOf(size).length();
        this.horizontalDivider = calculateHorizontalDivider();
    }

    @Override
    public void print() {
        printHeader();
        System.out.println(horizontalDivider);
        for (int i = 1; i <= size; i++) {
            printRow(i);
            System.out.println(horizontalDivider);
        }
    }

    /**
     * Prints the table header.
     */
    private void printHeader() {
        System.out.printf("%-" + headerWidth + "s|", "");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%" + cellWidth + CELL_SEPARATOR, i);
        }
        System.out.println();
    }

    /**
     * Calculates the horizontal divider.
     */
    private String calculateHorizontalDivider() {
        StringBuilder divider = new StringBuilder();
        divider.append("-".repeat(headerWidth)).append("+");
        for (int i = 1; i <= size; i++) {
            divider.append("-".repeat(cellWidth)).append("+");
        }
        return divider.toString();
    }

    /**
     * Prints the multiplication table rows.
     *
     * @param rowNumber the number of the row.
     */
    private void printRow(int rowNumber) {
        System.out.printf("%" + headerWidth + "d|", rowNumber);
        for (int j = 1; j <= size; j++) {
            System.out.printf("%" + cellWidth + "d|", rowNumber * j);
        }
        System.out.println();
    }
}


