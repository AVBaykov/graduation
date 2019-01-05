package ru.javawebinar.graduation.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.service.dish.DishService;
import ru.javawebinar.graduation.web.AbstractControllerTest;
import ru.javawebinar.graduation.web.json.JsonUtil;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.graduation.DishTestData.*;
import static ru.javawebinar.graduation.RestaurantTestData.REST1_ID;
import static ru.javawebinar.graduation.TestUtil.readFromJsonResultActions;
import static ru.javawebinar.graduation.TestUtil.userHttpBasic;
import static ru.javawebinar.graduation.UserTestData.ADMIN;
import static ru.javawebinar.graduation.UserTestData.USER;

class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishController.REST_URL + '/';

    @Autowired
    private DishService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getDishMatcher(DISH1));
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_REST1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_REST1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAllByRestaurantId(REST1_ID), DISH2, DISH3, DISH4, DISH5);
    }

    @Test
    void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_REST1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testUpdate() throws Exception {
        Dish updated = getUpdated();

        mockMvc.perform(put(REST_URL + DISH_REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(DISH_REST1_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Dish created = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Dish returned = readFromJsonResultActions(action, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAllByRestaurantId(REST1_ID), DISH1, DISH2, DISH3, DISH4, DISH5, created);
    }

    @Test
    void testCreateInvalid() throws Exception {
        Dish invalid = new Dish(null, null, null, null);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void testUpdateInvalid() throws Exception {
        Dish invalid = new Dish(DISH_REST1_ID, null, new BigDecimal("65.12341"), REST1_ID);
        mockMvc.perform(put(REST_URL + DISH_REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

}