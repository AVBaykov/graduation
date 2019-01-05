package ru.javawebinar.graduation.service.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.javawebinar.graduation.DishTestData;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.service.AbstractServiceTest;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.graduation.RestaurantTestData.*;

class RestaurantServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getCreated();
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), REST1, REST2, REST3, newRestaurant);
    }

    @Test
    void createWithDuplicateNameAddress() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant(null, "Гуси", "Город Н, Колочевская улица, 5")));
    }

    @Test
    void get() throws Exception {
        Restaurant restaurant = service.get(REST1_ID);
        assertMatch(restaurant, REST1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void delete() throws Exception {
        service.delete(REST1_ID);
        assertMatch(service.getAll(), REST2, REST3);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(REST1_ID), updated);
    }

    @Test
    void getAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, REST1, REST2, REST3);
    }

    @Test
    void getWithMenuOfDay() throws Exception {
        Restaurant restaurant = service.getWithMenuOfDay(REST1_ID);
        assertMatch(restaurant, REST1);
        DishTestData.assertMatch(restaurant.getMenu(), DishTestData.MENU1);
    }

    @Test
    void getWithMenuOfDayNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getWithMenuOfDay(1));
    }

    @Test
    void getAllWithMenuOfDay() throws Exception {
        List<Restaurant> all = service.getAllWithMenuOfDay();
        assertMatch(all, REST1, REST2, REST3);
        DishTestData.assertMatch(all.get(0).getMenu(), DishTestData.MENU1);
        DishTestData.assertMatch(all.get(1).getMenu(), DishTestData.MENU2);
        DishTestData.assertMatch(all.get(2).getMenu(), DishTestData.MENU3);
    }
}