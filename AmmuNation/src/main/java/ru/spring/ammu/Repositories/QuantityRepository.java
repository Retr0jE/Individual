package ru.spring.ammu.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spring.ammu.Models.Employee;
import ru.spring.ammu.Models.Quantity;

public interface QuantityRepository extends CrudRepository<Quantity, Long> {


}
