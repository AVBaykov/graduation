package ru.javawebinar.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.graduation.model.User;
import ru.javawebinar.graduation.repository.UserRepository;

public abstract class AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;


    public User get(int id) {
        log.info("get {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public User create(User user) {
        log.info("create {}", user);
        return userRepository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        userRepository.deleteById(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        userRepository.save(user);
    }


    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return userRepository.getByEmail(email);
    }

}
