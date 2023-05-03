package com.rrsys.productsapi.models;


import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductsEntity {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "varchar(255)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double amount;

    public ProductsEntity(){
    }
    public ProductsEntity(String name) {
        this.name = name;
    }

    public ProductsEntity(double amount) {
        this.amount = amount;
    }


    public ProductsEntity(UUID id, String name, String description, double amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
