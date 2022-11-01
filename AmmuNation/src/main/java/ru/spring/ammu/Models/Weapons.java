package ru.spring.ammu.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
public class Weapons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Введи вид оружия")
    private String name;

    @Positive(message = "Цена не может быть отрицательной")
    @NotNull(message = "Введи цену")
    private Integer price;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private WeaponModel weaponModel;

    @OneToMany(mappedBy = "weapons", fetch = FetchType.EAGER)
    private Collection<Quantity> quantities;

    @ManyToMany
    @JoinTable(name = "weapons_cheque",
            joinColumns = @JoinColumn(name = "weapons_id"),
            inverseJoinColumns = @JoinColumn(name = "cheque_id"))
    private List<Cheque> cheques;

    public Weapons() {
    }

    public Weapons(String name, Integer price, WeaponModel weaponModel, List<Cheque> cheques) {
        this.name = name;
        this.price = price;
        this.weaponModel = weaponModel;
        this.cheques = cheques;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public WeaponModel getWeaponModel() {
        return weaponModel;
    }

    public void setWeaponModel(WeaponModel weaponModel) {
        this.weaponModel = weaponModel;
    }

    public List<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(List<Cheque> cheques) {
        this.cheques = cheques;
    }
}
