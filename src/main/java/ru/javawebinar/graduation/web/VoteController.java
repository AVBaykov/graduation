package ru.javawebinar.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.model.VotePk;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.UserRepository;
import ru.javawebinar.graduation.repository.VoteRepository;

import java.net.URI;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/votes";

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{restaurant_id}")
    public ResponseEntity<Vote> vote(@PathVariable("restaurant_id") int restaurant_id) {
        VotePk votePk = new VotePk(SecurityUtil.authUserId());
        Vote vote = new Vote(votePk, restaurant_id);
        vote.setRestaurant(restaurantRepository.getOne(restaurant_id));
        vote.setUser(userRepository.getOne(SecurityUtil.authUserId()));
        Vote saved = voteRepository.save(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurant_id}")
                .buildAndExpand(saved.getRestaurantId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(saved);
    }
}
