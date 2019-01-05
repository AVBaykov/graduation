package ru.javawebinar.graduation.web.dish;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.service.dish.DishService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish) {
        log.info("create {}", dish);
        checkNew(dish);
        Dish created = service.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        log.info("get dish {}", id);
        return service.get(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("id") int id) {
        assureIdConsistent(dish, id);
        log.info("update {} with id={}", dish, id);
        service.update(dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @GetMapping(value = "/restaurants/{restaurant_id}")
    public List<Dish> getAllByRestaurantId(@PathVariable("restaurant_id") int restaurant_id) {
        log.info("getAll for restaurant {}", restaurant_id);
        return service.getAllByRestaurantId(restaurant_id);
    }
}
