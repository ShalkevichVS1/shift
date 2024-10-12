package com.shalkevich.projectLogic;

import com.shalkevich.interfaces.Table;
import com.shalkevich.projectLogic.MultiplicationTable;

/**
 * Factory class for creating tables.
 */
public class TableFactory {

    /**
     * Creates a multiplication table object of the given size.
     * @param size table size.
     * @return table object.
     */
    public static Table createTable(int size) {
        return new MultiplicationTable(size);
    }
}
