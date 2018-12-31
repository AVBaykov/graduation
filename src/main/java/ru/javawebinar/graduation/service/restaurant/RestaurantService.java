package ru.javawebinar.graduation.service.restaurant;

import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Restaurant getWithMenuOfDay(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    List<Restaurant> getAll();

    List<Restaurant> getAllWithMenuOfDay();
}
