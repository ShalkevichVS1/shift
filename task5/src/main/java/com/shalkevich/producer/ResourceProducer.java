package com.shalkevich.producer;

import com.shalkevich.util.Resource;
import com.shalkevich.util.LogEvent;
import com.shalkevich.storage.ResourceStorage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Представляет производителя ресурсов.
 * Реализует потоко-безопасные методы для производства ресурсов и их добавления на склад.
 */
@Getter
@Setter
@Slf4j
public class ResourceProducer extends Thread implements Producer {
    private int producerId;
    private boolean running = true;
    private final ResourceStorage storage;
    private final int producerTime;

    /**
     * Конструктор класса ResourceProducer.
     *
     * @param producerId   Идентификатор производителя.
     * @param storage      Экземпляр склада для хранения ресурсов.
     * @param producerTime Время производства одного ресурса в миллисекундах.
     */
    public ResourceProducer(int producerId, ResourceStorage storage, int producerTime) {
        this.producerId = producerId;
        this.storage = storage;
        this.producerTime = producerTime;
    }

    @Override
    public void run() {
        try {
            while (running) {
                synchronized (storage) {
                    while (storage.isFull() && running) {
                        logEvent(LogEvent.WAITING);
                        storage.wait(); // Ожидание, пока на складе появится место
                    }
                    if (!running) break;
                    produce();
                    storage.notifyAll(); // Уведомить потребителей о наличии нового ресурса
                    logEvent(LogEvent.RESUMED);
                    storage.wait(producerTime); // Ожидание времени производства
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void produce() {
        Resource resource = new Resource();
        storage.addResource(resource);
        logEvent(resource.getId());
    }

    @Override
    public void stopProducing() {
        running = false;
        synchronized (storage) {
            storage.notifyAll(); // Уведомить всех, чтобы выйти из состояния ожидания
        }
    }

    /**
     * Логирует событие без идентификатора ресурса.
     *
     * @param event Событие логирования.
     */
    private void logEvent(LogEvent event) {
        log.info("{} - Производитель {} {}", java.time.LocalTime.now(), producerId, event.getDescription());
    }

    /**
     * Логирует событие с идентификатором ресурса.
     *
     * @param resourceId Идентификатор ресурса.
     */
    private void logEvent(String resourceId) {
        log.info("{} - Производитель {} {} ресурс {}. Текущий размер склада: {}",
                java.time.LocalTime.now(),
                producerId, LogEvent.PRODUCED.getDescription(),
                resourceId, storage.getStorageSize());
    }
}
