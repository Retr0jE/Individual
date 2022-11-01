package ru.spring.ammu.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spring.ammu.Models.Cheque;
import ru.spring.ammu.Models.WeaponModel;

public interface WeaponModelRepository extends CrudRepository<WeaponModel, Long> {
}
