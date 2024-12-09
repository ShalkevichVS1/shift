package com.shalkevich.service;

/**
 * Factory for making tables.
 */
public class TableFactory {

    /**
     * Creates multiplication tables of a given size.
     *
     * @param size table size.
     * @return table object.
     */
    public static Table create(int size) {
        return new MultiplicationTable(size);
    }
}
