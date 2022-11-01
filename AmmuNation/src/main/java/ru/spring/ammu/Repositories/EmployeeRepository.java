package ru.spring.ammu.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spring.ammu.Models.Cheque;
import ru.spring.ammu.Models.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findByNameContaining(String name);
}
