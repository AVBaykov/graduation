package ru.javawebinar.graduation;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.graduation.model.Role;
import ru.javawebinar.graduation.model.User;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.model.VotePk;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.UserRepository;
import ru.javawebinar.graduation.repository.VoteRepository;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            UserRepository userRepository = (UserRepository) appCtx.getBean("userRepository");
            VoteRepository voteRepository = (VoteRepository) appCtx.getBean("voteRepository");
            RestaurantRepository restaurantRepository = appCtx.getBean(RestaurantRepository.class);

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
        }
    }
}
