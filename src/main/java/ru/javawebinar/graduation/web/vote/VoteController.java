package ru.javawebinar.graduation.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.graduation.AuthorizedUser;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.model.VotePk;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.UserRepository;
import ru.javawebinar.graduation.repository.VoteRepository;
import ru.javawebinar.graduation.to.VoteTo;
import ru.javawebinar.graduation.util.exception.VoteTimesUpException;

import java.net.URI;
import java.time.Clock;
import java.time.LocalTime;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/votes";
    private static final LocalTime VOTE_END_TIME = LocalTime.parse("11:00");
    private final Clock clock = Clock.systemDefaultZone();

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<VoteTo> vote(@PathVariable("restaurantId") int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser) {
        if (LocalTime.now(clock).isAfter(VOTE_END_TIME)) {
            throw new VoteTimesUpException("Vote time was up at 11:00");
        }
        VotePk votePk = new VotePk(authUser.getId());
        Vote vote = new Vote(votePk, restaurantId);
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        vote.setUser(userRepository.getOne(authUser.getId()));
        Vote saved = voteRepository.save(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(saved.getRestaurantId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(new VoteTo(saved));
    }
}
