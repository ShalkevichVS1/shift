package com.shalkevich;

import com.shalkevich.interfaces.Table;
import com.shalkevich.projectLogic.TableFactory;
import com.shalkevich.projectLogic.UserInput;

/**
 * A program for implementing a multiplication table
 * when entering a table size from 1 to 32.
 */

public class Main {
    public static void main(String[] args) {

        UserInput input = new UserInput();
        int size = input.getTableSize();

        Table table = TableFactory.createTable(size);
        table.print();
    }
}
