package ru.javawebinar.graduation.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class VotePk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Column(name = "user_id")
    private int userId;

    public VotePk() {
    }

    public VotePk(int userId) {
        this(LocalDate.now(), userId);
    }

    public VotePk(LocalDate date, int userId) {
        this.date = date;
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
