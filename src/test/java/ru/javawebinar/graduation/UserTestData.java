package ru.javawebinar.graduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.graduation.model.Role;
import ru.javawebinar.graduation.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.graduation.TestUtil.readFromJsonMvcResult;
import static ru.javawebinar.graduation.TestUtil.readListFromJsonMvcResult;
import static ru.javawebinar.graduation.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "votes", "password");
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "votes", "password").isEqualTo(expected);
    }

    public static ResultMatcher getUserMatcher(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static ResultMatcher getUserMatcher(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }
}
