package com.shalkevich;

import com.shalkevich.ui.UserInterface;
import lombok.extern.slf4j.Slf4j;

/**
 * Точка входа в приложение.
 */
@Slf4j
public class Main {

    /**
     * Точка входа в программу.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
        ui.close();
    }
}
