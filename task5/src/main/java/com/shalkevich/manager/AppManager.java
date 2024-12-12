package com.shalkevich.manager;

import com.shalkevich.producer.ResourceProducer;
import com.shalkevich.storage.ResourceStorage;
import com.shalkevich.consumer.ResourceConsumer;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

/**
 * Отвечает за управление запуском и остановкой производителей и потребителей.
 */
public class AppManager {
    private ResourceProducer[] producers;
    private ResourceConsumer[] consumers;

    /**
     * Запускает производителей и потребителей на основе конфигурационных параметров.
     */
    public void start() {
        Properties properties = new Properties();

        try (InputStream input = AppManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Unable to load configuration file.");
            return;
        }

        int producerCount = Integer.parseInt(properties.getProperty("producerCount"));
        int consumerCount = Integer.parseInt(properties.getProperty("consumerCount"));
        int producerTime = Integer.parseInt(properties.getProperty("producerTime"));
        int consumerTime = Integer.parseInt(properties.getProperty("consumerTime"));
        int storageSize = Integer.parseInt(properties.getProperty("storageSize"));

        ResourceStorage storage = new ResourceStorage(storageSize);
        producers = new ResourceProducer[producerCount];
        consumers = new ResourceConsumer[consumerCount];

        // Запуск производителей
        for (int i = 0; i < producerCount; i++) {
            producers[i] = new ResourceProducer(i + 1, storage, producerTime);
            producers[i].start();
        }

        // Запуск потребителей
        for (int i = 0; i < consumerCount; i++) {
            consumers[i] = new ResourceConsumer(i + 1, storage, consumerTime);
            consumers[i].start();
        }
    }

    /**
     * Останавливает производителей и потребителей, корректно завершает их работу.
     */
    public void stop() {
        for (ResourceProducer producer : producers) {
            producer.stopProducing();
        }

        for (ResourceConsumer consumer : consumers) {
            consumer.stopConsuming();
        }

        for (ResourceProducer producer : producers) {
            try {
                producer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (ResourceConsumer consumer : consumers) {
            try {
                consumer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
