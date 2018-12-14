package ru.javawebinar.graduation.model;

import java.math.BigDecimal;

public class Dish extends AbstractNamedEntity {

    private BigDecimal price;

    protected Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.price = price;
    }
}
