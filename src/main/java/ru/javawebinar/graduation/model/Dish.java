package ru.javawebinar.graduation.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Digits(integer = 6, fraction = 2, message = "price must be not exceed 6 digits to and 2 digits after point")
    @NotNull
    private BigDecimal price;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date = LocalDate.now();

    @Column(name = "restaurant_id", nullable = false)
    @NotNull
    private Integer restaurantId;

    public Dish(){}

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getRestaurantId());
    }

    public Dish(Integer id, String name, BigDecimal price, Integer restaurantId) {
        super(id, name);
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public Dish(Integer id, String name, BigDecimal price, LocalDate date, Integer restaurantId) {
        super(id, name);
        this.price = price;
        this.date = date;
        this.restaurantId = restaurantId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", date=" + date +
                ", restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
