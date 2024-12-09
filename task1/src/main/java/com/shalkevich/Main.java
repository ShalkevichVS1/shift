package com.shalkevich;

import com.shalkevich.service.Table;
import com.shalkevich.service.TableFactory;
import com.shalkevich.service.UserInput;

/**
 * A program for implementing a multiplication table
 * when entering a table size from 1 to 32.
 */
public class Main {

    public static void main(String[] args) {

        UserInput input = new UserInput();
        int size = input.getTableSize();

        Table table = TableFactory.create(size);
        table.print();
    }
}
