package ru.javawebinar.graduation.model;

import java.time.LocalDate;
import java.util.Set;

public class Menu extends AbstractBaseEntity {

    private LocalDate date;

    private Set<Dish> dishes;

    private Restaurant restaurant;
}
