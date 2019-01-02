package ru.javawebinar.graduation.service.dish;

import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish create(Dish dish);

    void delete(int id) throws NotFoundException;

    Dish get(int id) throws NotFoundException;

    void update(Dish dish);

    List<Dish> getAll(int restaurantId);
}
