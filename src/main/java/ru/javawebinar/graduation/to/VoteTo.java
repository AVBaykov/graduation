package ru.javawebinar.graduation.to;

import ru.javawebinar.graduation.model.Vote;

import java.time.LocalDate;

public class VoteTo {

    private LocalDate date;

    private Integer userId;

    private Integer restaurantId;

    public VoteTo() {
    }

    public VoteTo(Vote vote) {
        this.date = vote.getVotePk().getDate();
        this.userId = vote.getVotePk().getUserId();
        this.restaurantId = vote.getRestaurantId();
    }

}
