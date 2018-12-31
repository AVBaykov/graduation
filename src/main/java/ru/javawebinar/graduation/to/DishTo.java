package ru.javawebinar.graduation.to;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DishTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private LocalDate date = LocalDate.now();

    @NotNull
    private Integer restaurantId;

    public DishTo() {
    }

    public DishTo(Integer id, String name, BigDecimal price, LocalDate date, Integer restaurantId) {
        super(id);
        this.name = name;
        this.price = price;
        this.date = date;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "DishTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}
