package ru.javawebinar.graduation.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import ru.javawebinar.graduation.web.AbstractControllerTest;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.graduation.RestaurantTestData.REST1_ID;
import static ru.javawebinar.graduation.TestUtil.userHttpBasic;
import static ru.javawebinar.graduation.UserTestData.ADMIN;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteController controller;

    @Test
    void testVote() throws Exception {
        ReflectionTestUtils.setField(controller, "clock", Clock.fixed(Instant.parse("2018-01-01T10:00:00Z"), ZoneOffset.UTC));
        mockMvc.perform(post(REST_URL + REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testAfterTime() throws Exception {
        ReflectionTestUtils.setField(controller, "clock", Clock.fixed(Instant.parse("2018-01-01T11:01:00Z"), ZoneOffset.UTC));
        mockMvc.perform(post(REST_URL + REST1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isConflict())
                .andDo(print());
    }

}