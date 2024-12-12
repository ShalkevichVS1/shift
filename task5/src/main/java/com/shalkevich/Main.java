package com.shalkevich;

import com.shalkevich.manager.AppManager;

/**
 * Используется для запуска приложения.
 */
public class Main {
    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        appManager.start();

        // Приложение будет работать бесконечно, производя и потребляя ресурсы
        // Завершение работы можно предусмотреть при необходимости

        // Пример для завершения работы через 10 секунд
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        appManager.stop();
    }
}
