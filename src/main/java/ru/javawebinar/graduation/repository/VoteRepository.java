package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.model.VotePk;

public interface VoteRepository extends JpaRepository<Vote, VotePk> {
}
