package ru.javawebinar.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.model.User;
import ru.javawebinar.graduation.repository.UserRepository;

import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String REST_URL = "/users";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAll() {
        log.info("getAll");
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByMail(@RequestParam("email") String email) {
        Assert.notNull(email, "email must not be null");
        log.info("getByEmail {}", email);
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }
}
