package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.graduation.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);
}
