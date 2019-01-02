package ru.javawebinar.graduation.service.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.graduation.model.Dish;
import ru.javawebinar.graduation.repository.DishRepository;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish create(Dish dish) {
        Assert.notNull(dish, "restaurant must not be null");
        return dishRepository.save(dish);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.delete(id) != 0, id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(dishRepository.findById(id).orElse(null), id);
    }

    @Override
    public void update(Dish dish) {
        dishRepository.save(dish);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return dishRepository.getAll(restaurantId);
    }
}
