package ru.javawebinar.graduation.service.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.service.AbstractServiceTest;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.graduation.DishTestData.assertMatch;
import static ru.javawebinar.graduation.DishTestData.*;
import static ru.javawebinar.graduation.RestaurantTestData.*;

class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;


    @Test
    void create() throws Exception {
        Dish newDish = new Dish(null, "Шаурма", new BigDecimal("100.00"), REST1_ID);
        Dish created = service.create(newDish);
        newDish.setId(created.getId());
        assertMatch(service.getAll(REST1_ID), DISH1, DISH2, DISH3, DISH4, DISH5, newDish);
    }

    @Test
    void get() throws Exception {
        Dish Dish = service.get(DISH_REST1_ID);
        assertMatch(Dish, DISH1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void delete() throws Exception {
        service.delete(DISH_REST1_ID);
        assertMatch(service.getAll(REST1_ID), DISH2, DISH3, DISH4, DISH5);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void update() throws Exception {
        Dish updated = new Dish(DISH1);
        updated.setName("Updated");
        updated.setPrice(new BigDecimal("65.00"));
        service.update(new Dish(updated));
        assertMatch(service.get(DISH_REST1_ID), updated);
    }

    @Test
    void getAll() throws Exception {
        List<Dish> all = service.getAll(REST1_ID);
        assertMatch(all, MENU1);
    }

}