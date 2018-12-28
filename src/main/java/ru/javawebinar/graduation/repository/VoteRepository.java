package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.model.VotePk;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, VotePk> {
}
