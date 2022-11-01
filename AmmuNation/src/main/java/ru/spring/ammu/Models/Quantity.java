package ru.spring.ammu.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "укажите кол-во товара")
    private Integer quantity;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Weapons weapons;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Warehouse warehouse;

    public Quantity(){

    }

    public Quantity(Integer quantity, Weapons weapons, Warehouse warehouse) {
        this.quantity = quantity;
        this.weapons = weapons;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Weapons getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapons weapons) {
        this.weapons = weapons;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
