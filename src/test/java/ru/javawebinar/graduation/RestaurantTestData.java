package ru.javawebinar.graduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.graduation.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.graduation.TestUtil.readFromJsonMvcResult;
import static ru.javawebinar.graduation.TestUtil.readListFromJsonMvcResult;
import static ru.javawebinar.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int REST1_ID = START_SEQ + 2;
    public static final int REST2_ID = START_SEQ + 3;
    public static final int REST3_ID = START_SEQ + 4;

    public static final Restaurant REST1 = new Restaurant(REST1_ID, "McDonalds", "Город Н, Центральная площадь 30");
    public static final Restaurant REST2 = new Restaurant(REST2_ID, "Теремок", "Город Н, Кутувоская улица, 35");
    public static final Restaurant REST3 = new Restaurant(REST3_ID, "Гуси", "Город Н, Колочевская улица, 5");

    public static Restaurant getCreated() {
        return new Restaurant(null, "Шаурма", "Москва Лубянка 33");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(REST1_ID, "Updated", "New address");
    }


    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"menu", "votes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu", "votes").isEqualTo(expected);
    }

    public static ResultMatcher getRestaurantMatcher(Restaurant expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getRestaurantMatcher(Restaurant... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Restaurant.class), List.of(expected));
    }
}
