package com.sda.project.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userFirstName;

    private String userLastName;

    private LocalDateTime orderDate;

    private double total;

    private OrderStatus status;

    private String userAddressCountry;

    private String userAddressCounty;

    private String userAddressPostalCode;

    private String userAddressLocality;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable=false, updatable=false)
    private User user;

    @OneToOne
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;

    @OneToOne(mappedBy = "order",
            fetch = FetchType.LAZY)
    private Payment payment;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Order setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public Order setTotal(double subtotal) {
        this.total = subtotal;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public String getUserAddressCountry() {
        return userAddressCountry;
    }

    public Order setUserAddressCountry(String userAddressCountry) {
        this.userAddressCountry = userAddressCountry;
        return this;
    }

    public String getUserAddressCounty() {
        return userAddressCounty;
    }

    public Order setUserAddressCounty(String userAddressCounty) {
        this.userAddressCounty = userAddressCounty;
        return this;
    }

    public String getUserAddressPostalCode() {
        return userAddressPostalCode;
    }

    public Order setUserAddressPostalCode(String userAddressPostalCode) {
        this.userAddressPostalCode = userAddressPostalCode;
        return this;
    }

    public String getUserAddressLocality() {
        return userAddressLocality;
    }

    public Order setUserAddressLocality(String userAddressLocality) {
        this.userAddressLocality = userAddressLocality;
        return this;
    }
}
