package ru.javawebinar.graduation;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.graduation.model.*;
import ru.javawebinar.graduation.repository.DishRepository;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.UserRepository;
import ru.javawebinar.graduation.repository.VoteRepository;
import ru.javawebinar.graduation.service.restaurant.RestaurantServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            UserRepository userRepository = (UserRepository) appCtx.getBean("userRepository");
            VoteRepository voteRepository = (VoteRepository) appCtx.getBean("voteRepository");
            RestaurantRepository restaurantRepository = appCtx.getBean(RestaurantRepository.class);
            DishRepository dishRepository = appCtx.getBean(DishRepository.class);

            User user = new User(null, "User1", "user@yandex1.ru", "password", Role.ROLE_USER);

            userRepository.save(user);

            System.out.println(userRepository.getByEmail("user@yandex.ru") + "наш юзер");

            VotePk votePk = new VotePk(100000);
            Vote vote = new Vote(votePk, 100002);
            vote.setRestaurant(restaurantRepository.getOne(100002));
            Vote saved = voteRepository.save(vote);

            System.out.println(saved + "сохраненный голос");

            vote = new Vote(votePk, 100003);
            vote.setRestaurant(restaurantRepository.getOne(100003));
            Vote updated = voteRepository.save(vote);

            System.out.println(updated + "обновленный");

            Dish newDish = new Dish(null, "Новый Чизбургер", new BigDecimal(100.20), LocalDate.now().plusDays(1), 100002);
            dishRepository.save(newDish);

            RestaurantServiceImpl restaurantService = (RestaurantServiceImpl) appCtx.getBean("restaurantServiceImpl");

            Restaurant restaurant = restaurantService.getWithMenuOfDay(100002);

            restaurant.getMenu().forEach(System.out::println);

            List<Restaurant> restaurantList = restaurantService.getAllWithMenuOfDay();

            restaurantList.forEach(r -> System.out.println(r.getMenu().size()));
        }
    }
}
