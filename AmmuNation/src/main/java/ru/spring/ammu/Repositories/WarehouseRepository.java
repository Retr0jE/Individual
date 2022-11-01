package ru.spring.ammu.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spring.ammu.Models.Cheque;
import ru.spring.ammu.Models.Warehouse;
import ru.spring.ammu.Models.Weapons;

import java.util.List;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    public List<Warehouse> findByAddressContaining(String name);
}
