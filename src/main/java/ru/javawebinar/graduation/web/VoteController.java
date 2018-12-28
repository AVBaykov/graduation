package ru.javawebinar.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.model.VotePk;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.VoteRepository;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Vote vote(int restaurant_id) {
        VotePk votePk = new VotePk(SecurityUtil.authUserId());
        Vote vote = new Vote(votePk, restaurant_id);
        vote.setRestaurant(restaurantRepository.getOne(restaurant_id));
        return voteRepository.save(vote);
    }
}
