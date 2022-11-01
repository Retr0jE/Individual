package ru.spring.ammu.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "укажите контактное лицо поставщика")
    private String name;
    @NotEmpty(message = "введи номер телефона поставщика")
    private String phone;


    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private Collection<Warehouse> warehouses;

    public Supplier(){

    }

    public Supplier(String name, String phone, Collection<Warehouse> warehouses) {
        this.name = name;
        this.phone = phone;
        this.warehouses = warehouses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Collection<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
