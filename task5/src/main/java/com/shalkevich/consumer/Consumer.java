package com.shalkevich.consumer;

/**
 * Интерфейс Consumer определяет методы для потребителей ресурсов.
 */
public interface Consumer {
    /**
     * Метод для потребления ресурса.
     *
     * @throws InterruptedException в случае прерывания процесса.
     */
    void consume() throws InterruptedException;

    /**
     * Метод для остановки потребления ресурсов.
     */
    void stopConsuming();
}
