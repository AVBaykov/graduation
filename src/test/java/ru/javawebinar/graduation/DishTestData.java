package ru.javawebinar.graduation;

import ru.javawebinar.graduation.model.Dish;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.graduation.RestaurantTestData.*;
import static ru.javawebinar.graduation.model.AbstractBaseEntity.START_SEQ;


public class DishTestData {

    public static final int DISH_REST1_ID = START_SEQ + 5;
    public static final int DISH_REST2_ID = START_SEQ + 10;
    public static final int DISH_REST3_ID = START_SEQ + 15;

    public static final Dish DISH1 = new Dish(DISH_REST1_ID, "Гамбургер", new BigDecimal("60.00"), REST1_ID);
    public static final Dish DISH2 = new Dish(DISH_REST1_ID + 1, "Чизбургер", new BigDecimal("65.00"), REST1_ID);
    public static final Dish DISH3 = new Dish(DISH_REST1_ID + 2, "Чикенбургер", new BigDecimal("62.00"), REST1_ID);
    public static final Dish DISH4 = new Dish(DISH_REST1_ID + 3, "Кока-кола", new BigDecimal("30.00"), REST1_ID);
    public static final Dish DISH5 = new Dish(DISH_REST1_ID + 4, "Спрайт", new BigDecimal("30.00"), REST1_ID);
    public static final Dish DISH6 = new Dish(DISH_REST2_ID, "Блин \"Пахарь\"", new BigDecimal("51.00"), REST2_ID);
    public static final Dish DISH7 = new Dish(DISH_REST2_ID + 1, "Блин \"Жнец\"", new BigDecimal("51.00"), REST2_ID);
    public static final Dish DISH8 = new Dish(DISH_REST2_ID + 2, "Блин \"На дуде игрец\"", new BigDecimal("52.00"), REST2_ID);
    public static final Dish DISH9 = new Dish(DISH_REST2_ID + 3, "Морс", new BigDecimal("25.00"), REST2_ID);
    public static final Dish DISH10 = new Dish(DISH_REST2_ID + 4, "Квас", new BigDecimal("30.20"), REST2_ID);
    public static final Dish DISH11 = new Dish(DISH_REST3_ID, "Суп", new BigDecimal("51.00"), REST3_ID);
    public static final Dish DISH12 = new Dish(DISH_REST3_ID + 1, "Котлеты", new BigDecimal("51.00"), REST3_ID);
    public static final Dish DISH13 = new Dish(DISH_REST3_ID + 2, "Пюрешка", new BigDecimal("52.00"), REST3_ID);
    public static final Dish DISH14 = new Dish(DISH_REST3_ID + 3, "Компот ягодный", new BigDecimal("25.00"), REST3_ID);
    public static final Dish DISH15 = new Dish(DISH_REST3_ID + 4, "Компот из сухофруктов", new BigDecimal("30.20"), REST3_ID);

    public static final List<Dish> MENU1 = List.of(DISH1, DISH2, DISH3, DISH4, DISH5);
    public static final List<Dish> MENU2 = List.of(DISH6, DISH7, DISH8, DISH9, DISH10);
    public static final List<Dish> MENU3 = List.of(DISH11, DISH12, DISH13, DISH14, DISH15);


    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
