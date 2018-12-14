package ru.javawebinar.graduation.model;

public class Restaurant extends AbstractNamedEntity {

    private String address;

    private String phone;

    private String email;

    public Restaurant(Integer id, String name, String address, String phone, String email) {
        super(id, name);
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
