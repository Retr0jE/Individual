package ru.spring.ammu.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spring.ammu.Models.Cheque;
import ru.spring.ammu.Models.Supplier;
import ru.spring.ammu.Models.Weapons;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    public List<Supplier> findByNameContaining(String name);
}
