package ru.javawebinar.graduation.service.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @Override
    public Restaurant getWithMenuOfDay(int id) throws NotFoundException {
        LocalDate today = LocalDate.now();
        return restaurantRepository.getWithMenuOfDay(id, today);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    @Override
    public List<Restaurant> getAllWithMenuOfDay() {
        LocalDate today = LocalDate.now();
        return restaurantRepository.getAllWithMenuOfDay(today);
    }
}
