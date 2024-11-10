package com.shalkevich.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Предоставляет общий функционал для геометрических фигур.
 */
public abstract class AbstractFigure implements Figure {
    
    private static final Logger logger = LoggerFactory.getLogger(AbstractFigure.class);

    @Override
    public String getSpecificCharacteristics() {
        logger.info("Getting specific characteristics");
        return "";
    }
}
