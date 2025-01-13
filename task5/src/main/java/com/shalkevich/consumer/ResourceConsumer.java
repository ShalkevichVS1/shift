package com.shalkevich.consumer;

import com.shalkevich.util.Resource;
import com.shalkevich.util.LogEvent;
import com.shalkevich.storage.ResourceStorage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Представляет потребителя ресурсов.
 * Реализует потоко-безопасные методы для потребления ресурсов со склада.
 */
@Getter
@Setter
@Slf4j
public class ResourceConsumer extends Thread implements Consumer {
    private int consumerId;
    private boolean running = true;
    private final ResourceStorage storage;
    private final int consumerTime;

    /**
     * Конструктор класса ResourceConsumer.
     *
     * @param consumerId   Идентификатор потребителя.
     * @param storage      Экземпляр склада для хранения ресурсов.
     * @param consumerTime Время потребления одного ресурса в миллисекундах.
     */
    public ResourceConsumer(int consumerId, ResourceStorage storage, int consumerTime) {
        this.consumerId = consumerId;
        this.storage = storage;
        this.consumerTime = consumerTime;
    }

    @Override
    public void run() {
        try {
            while (running) {
                synchronized (storage) {
                    while (storage.isEmpty() && running) {
                        logEvent(LogEvent.WAITING);
                        storage.wait(); // Ожидание, пока на складе появится ресурс
                    }
                    if (!running) break;
                    consume();
                    storage.notifyAll(); // Уведомить производителей о том, что ресурс забран
                    logEvent(LogEvent.RESUMED);
                    storage.wait(consumerTime); // Ожидание времени потребления
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void consume() {
        Resource resource = storage.getResource();
        if (resource != null) {
            logEvent(resource.getId());
        }
    }

    @Override
    public void stopConsuming() {
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
        log.info("{} - Потребитель {} {}", java.time.LocalTime.now(), consumerId, event.getDescription());
    }

    /**
     * Логирует событие с идентификатором ресурса.
     *
     * @param resourceId Идентификатор ресурса.
     */
    private void logEvent(String resourceId) {
        log.info("{} - Потребитель {} {} ресурс {}. Текущий размер склада: {}",
                java.time.LocalTime.now(), consumerId,
                LogEvent.CONSUMED.getDescription(),
                resourceId, storage.getStorageSize());
    }
}
