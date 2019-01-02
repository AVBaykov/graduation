package ru.javawebinar.graduation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class VotePk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "date")
    @NotNull
    private LocalDate date = LocalDate.now();

    @Column(name = "user_id")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotePk votePk = (VotePk) o;
        return date.equals(votePk.date) &&
                userId.equals(votePk.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, userId);
    }

    @Override
    public String toString() {
        return "VotePk{" +
                "date=" + date +
                ", userId=" + userId +
                '}';
    }
}
