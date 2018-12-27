package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.graduation.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
