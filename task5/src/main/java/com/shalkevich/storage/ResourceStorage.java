package com.shalkevich.storage;

import com.shalkevich.util.Resource;

import java.util.LinkedList;
import java.util.Queue;

import lombok.extern.slf4j.Slf4j;

/**
 * Представляет склад для хранения ресурсов.
 * Реализует потоко-безопасные методы для добавления и получения ресурсов.
 */
@Slf4j
public class ResourceStorage {
    private final int storageSize;
    private final Queue<Resource> storage = new LinkedList<>();

    /**
     * Конструктор класса ResourceStorage.
     *
     * @param storageSize Максимальный размер склада.
     */
    public ResourceStorage(int storageSize) {
        this.storageSize = storageSize;
    }

    /**
     * Добавляет ресурс на склад.
     * Логирует добавление ресурса и уведомляет все потоки о добавлении.
     *
     * @param resource Ресурс для добавления на склад.
     */
    public synchronized void addResource(Resource resource) {
        storage.add(resource);
        log.info("{} - Ресурс {} добавлен на склад. Текущий размер склада: {}",
                java.time.LocalTime.now(), resource.getId(), getStorageSize());
        notifyAll();
    }

    /**
     * Забирает ресурс со склада.
     * Логирует удаление ресурса и возвращает его.
     *
     * @return Ресурс, удаленный со склада, или null, если склад пуст.
     */
    public synchronized Resource getResource() {
        Resource resource = storage.poll();
        log.info("{} - Ресурс {} убран со склада. Текущий размер склада: {}",
                java.time.LocalTime.now(), resource != null ? resource.getId() : "null", getStorageSize());
        return resource;
    }

    /**
     * Проверяет, полон ли склад.
     *
     * @return true, если склад полон, иначе false.
     */
    public synchronized boolean isFull() {
        return storage.size() == storageSize;
    }

    /**
     * Проверяет, пуст ли склад.
     *
     * @return true, если склад пуст, иначе false.
     */
    public synchronized boolean isEmpty() {
        return storage.isEmpty();
    }

    /**
     * Возвращает текущий размер склада.
     *
     * @return Текущий размер склада.
     */
    public synchronized int getStorageSize() {
        return storage.size();
    }
}
