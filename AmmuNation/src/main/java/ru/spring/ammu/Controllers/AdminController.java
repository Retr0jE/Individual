package ru.spring.ammu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.ammu.Models.Employee;
import ru.spring.ammu.Models.Role;
import ru.spring.ammu.Repositories.EmployeeRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admindir")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String employee(Model model)
    {
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute("listEmployee",listEmployee);

        return "admindir/admin";
    }

    @GetMapping("/addemployee")
    public String employeeAddView(Model model, Employee employee)
    {

        model.addAttribute("roleList", Role.values());
        return "/admindir/addemployee";
    }

    @PostMapping("/addemployee")
    public String employeeAdd(@Valid Employee employee,
                            BindingResult bindingResult,
                            @RequestParam(name = "roles[]") String[] userRoles,

//            @RequestParam String name,
//                         @RequestParam Integer year,
//                         @RequestParam String genre,
//                         @RequestParam String country,
//                            @RequestParam Integer budget,
                            Model model)
    {



        for (String roleOne : userRoles)
        {
            employee.getRoles().add(Role.valueOf(roleOne));
        }

        if(bindingResult.hasErrors())
        {
            model.addAttribute("roleList", Role.values());
            return "/admindir/addemployee";
        }

        employee.setActive(true);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return "redirect:/admindir";
    }

    @GetMapping("/detail/{id}")
    public String employeeDetail(@PathVariable Long id,
                               Model model)
    {
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> res = new ArrayList<>();

        employee.ifPresent(res::add);

        model.addAttribute("employee", res);
        return "/admindir/employee-detail";
    }

    @GetMapping("/filter")
    public String employeeFilter(@RequestParam(name = "searchName") String name,
                               Model model)
    {
        List<Employee> employees = employeeRepository.findByNameContaining(name);

        model.addAttribute("employee", employees);

        return "admindir/employee-filter";
    }

    @GetMapping("/del/{id}")
    public String employeeDel(@PathVariable Long id)
    {
        employeeRepository.deleteById(id);
        return "redirect:/admindir";
    }

    @GetMapping("/edit/{id}")
    public String employeeEditView(@PathVariable Long id, Model model, Employee employee)
    {
        model.addAttribute("roleList", Role.values());
        employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return "/admindir/edit-employee";
    }


    @PostMapping("/edit/{id}")
    public String employeeEdit(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam(required = false) String patronymic,
                             @RequestParam String username,
                             @RequestParam(required = false) String password,
                             Model model, @Valid Employee employee, BindingResult bindingResult,
                             @RequestParam(name = "roles[]") String[] userRoles)
    {

        for (String roleOne : userRoles)
        {
            employee.getRoles().add(Role.valueOf(roleOne));
        }
        if(bindingResult.hasErrors())
        {
            return "admindir/edit-employee";
        }
        employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setUsername(username);

        employeeRepository.save(employee);

        return "redirect:/admindir/detail/" + employee.getId();
    }
}
