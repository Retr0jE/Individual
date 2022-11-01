package ru.spring.ammu.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "введи адрес склада")
    private String address;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Supplier supplier;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER)
    private Collection<Quantity> quantities;

    public Warehouse()
    {

    }

    public Warehouse(String address, Supplier supplier, Collection<Quantity> quantities) {
        this.address = address;
        this.supplier = supplier;
        this.quantities = quantities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Collection<Quantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(Collection<Quantity> quantities) {
        this.quantities = quantities;
    }
}
