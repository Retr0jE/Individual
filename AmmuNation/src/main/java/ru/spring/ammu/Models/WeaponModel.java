package ru.spring.ammu.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
@Entity
public class WeaponModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "введи название модели")
    private String nameModel;
    @NotEmpty(message = "введи тип оружия")
    private String type;
    @NotEmpty(message = "введи материал")
    private String material;



    @OneToMany(mappedBy = "weaponModel", fetch = FetchType.EAGER)
    private Collection<Weapons> weapons;

    public WeaponModel(){

    }


    public WeaponModel(String nameModel, String type, String material, Collection<Weapons> weapons) {
        this.nameModel = nameModel;
        this.type = type;
        this.material = material;
        this.weapons = weapons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Collection<Weapons> getWeapons() {
        return weapons;
    }

    public void setWeapons(Collection<Weapons> weapons) {
        this.weapons = weapons;
    }
}
