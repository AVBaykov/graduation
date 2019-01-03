package ru.javawebinar.graduation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "votes")
public class Vote {

    @EmbeddedId
    private VotePk votePk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "restaurant_id", insertable = false, updatable = false, nullable = false)
    private Integer restaurantId;

    public Vote(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Vote(VotePk votePk, Integer restaurantId) {
        this.votePk = votePk;
        this.restaurantId = restaurantId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public VotePk getVotePk() {
        return votePk;
    }

    public void setVotePk(VotePk votePk) {
        this.votePk = votePk;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "votePk=" + votePk +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
