package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.graduation.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
