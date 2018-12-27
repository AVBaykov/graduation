package ru.javawebinar.graduation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address")
    @NotNull
    private String address;


    @Column(name = "phone")
    @NotNull
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Dish> menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Vote> votes;

    public Restaurant() {

    }

    public Restaurant(Integer id, String name, String address, String phone) {
        super(id, name);
        this.address = address;
        this.phone = phone;
    }
}
