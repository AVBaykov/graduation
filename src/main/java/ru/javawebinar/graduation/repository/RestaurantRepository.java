package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menu d WHERE r.id=?1 AND d.date=?2")
    Restaurant getWithMenuOfDay(int id, LocalDate date);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menu d WHERE d.date=?1")
    List<Restaurant> getAllWithMenuOfDay(LocalDate date);
}
