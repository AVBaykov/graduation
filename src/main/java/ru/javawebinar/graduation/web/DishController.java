package ru.javawebinar.graduation.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.service.dish.DishService;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;


    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        log.info("get dish {}", id);
        return service.get(id);
    }
}
