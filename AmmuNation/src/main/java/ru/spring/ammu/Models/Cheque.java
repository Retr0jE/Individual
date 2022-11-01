package ru.spring.ammu.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
@Entity
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "введи ИНН")
    private String tin;

    @NotNull(message = "введи дату")
    private Date dateissue;


    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Employee employee;

    @ManyToMany
    @JoinTable(name = "weapons_cheque",
            joinColumns = @JoinColumn(name = "cheque_id"),
            inverseJoinColumns = @JoinColumn(name = "weapons_id"))
    private List<Weapons> weapons;


    public Cheque(){

    }

    public Cheque(String tin, Date dateissue, Employee employee, List<Weapons> weapons) {
        this.tin = tin;
        this.dateissue = dateissue;
        this.employee = employee;
        this.weapons = weapons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public Date getDateissue() {
        return dateissue;
    }

    public void setDateissue(Date dateissue) {
        this.dateissue = dateissue;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Weapons> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapons> weapons) {
        this.weapons = weapons;
    }
}
