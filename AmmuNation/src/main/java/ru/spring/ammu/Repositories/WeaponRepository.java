package ru.spring.ammu.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spring.ammu.Models.Cheque;
import ru.spring.ammu.Models.Employee;
import ru.spring.ammu.Models.Weapons;

import java.util.List;

public interface WeaponRepository extends CrudRepository<Weapons, Long> {

    public List<Weapons> findByNameContaining(String name);
}
