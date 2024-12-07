package com.shalkevich.description;

import com.shalkevich.figures.Figure;

/**
 * Интерфейс для всех описательных сервисов фигур.
 */
public interface DescriptionService {
    String generateDescription(Figure figure);
}
