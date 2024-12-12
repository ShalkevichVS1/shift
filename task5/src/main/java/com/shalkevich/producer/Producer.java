package com.shalkevich.producer;

/**
 * Интерфейс Producer определяет методы для производителей ресурсов.
 */
public interface Producer {
    /**
     * Метод для производства ресурса.
     *
     * @throws InterruptedException в случае прерывания процесса.
     */
    void produce() throws InterruptedException;

    /**
     * Метод для остановки производства ресурсов.
     */
    void stopProducing();
}
