package ru.spring.ammu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.ammu.Models.*;
import ru.spring.ammu.Repositories.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sellerdir")
@PreAuthorize("hasAnyAuthority('SELLER')")
public class SellerController {

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    WeaponModelRepository weaponModelRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ChequeRepository chequeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("")
    public String weapons(Model model)
    {
        Iterable<Weapons> listWeapons = weaponRepository.findAll();
        model.addAttribute("listWeapons",listWeapons);
        return "sellerdir/seller";
    }

    @GetMapping("/checkcheque")
    public String chequeView(Model model)
    {

        Iterable<Cheque> cheque = chequeRepository.findAll();


        model.addAttribute("cheque", cheque);
        return "/sellerdir/checkcheque";
    }

    @GetMapping("/addweapon")
    public String weaponsAddView(Model model, WeaponModel weaponModel, Weapons weapons)
    {
        Iterable<WeaponModel> weaponModels = weaponModelRepository.findAll();
        model.addAttribute("modelList", weaponModels);
        return "sellerdir/add-weapon";
    }
    @GetMapping("/addweaponmodel")
    public String weaponsModelAddView(Model model, WeaponModel weaponModel)
    {
        return "sellerdir/add-weaponmodel";
    }

    @PostMapping("/addweaponmodel")
    public String weaponModelAdd(@Valid WeaponModel weaponModel,
                            BindingResult bindingResult,

//            @RequestParam String name,
//                         @RequestParam Integer year,
//                         @RequestParam String genre,
//                         @RequestParam String country,
//                            @RequestParam Integer budget,
                            Model model)
    {
        if(bindingResult.hasErrors())
        {

            return "sellerdir/add-weaponmodel";
        }

        weaponModelRepository.save(weaponModel);

        return "redirect:/sellerdir";
    }

    @PostMapping("/addweapon")
    public String weaponAdd(@Valid Weapons weapons,
                              BindingResult bindingResult,
                            @RequestParam(name = "weaponModel") Long weaponModel_id,

//            @RequestParam String name,
//                         @RequestParam Integer year,
//                         @RequestParam String genre,
//                         @RequestParam String country,
//                            @RequestParam Integer budget,
                              Model model)
    {

        if(bindingResult.hasErrors())
        {
            Iterable<WeaponModel> weaponModels = weaponModelRepository.findAll();
            model.addAttribute("modelList", weaponModels);
            return "sellerdir/add-weapon";
        }

        WeaponModel weaponModel = weaponModelRepository.findById(weaponModel_id).orElseThrow();

        weapons.setWeaponModel(weaponModel);

        weaponRepository.save(weapons);

        return "redirect:/sellerdir";
    }

    @GetMapping("/detail/{id}")
    public String weaponDetail(@PathVariable Long id,
                                 Model model)
    {
        Optional<Weapons> weapons = weaponRepository.findById(id);
        ArrayList<Weapons> res = new ArrayList<>();

        weapons.ifPresent(res::add);

        model.addAttribute("weapons", res);
        return "/sellerdir/weapon-detail";
    }

    @GetMapping("/filter")
    public String weaponFilter(@RequestParam(name = "searchName") String name,
                                 Model model)
    {
        List<Weapons> weapons = weaponRepository.findByNameContaining(name);

        model.addAttribute("weapons", weapons);

        return "sellerdir/weapon-filter";
    }

    @GetMapping("/del/{id}")
    public String weaponDel(@PathVariable Long id)
    {
        weaponRepository.deleteById(id);
        return "redirect:/sellerdir";
    }

    @GetMapping("/edit/{id}")
    public String weaponsEditView(@PathVariable Long id, Model model, Weapons weapons)
    {
        Iterable<WeaponModel> weaponModels = weaponModelRepository.findAll();
        model.addAttribute("modelList", weaponModels);
        weapons = weaponRepository.findById(id).orElseThrow();
        model.addAttribute("weapons", weapons);
        return "/sellerdir/edit-weapon";
    }


    @PostMapping("/edit/{id}")
    public String weaponsEdit(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam Integer price,
                               Model model, @Valid Weapons weapons, BindingResult bindingResult
                               )
    {


        if(bindingResult.hasErrors())
        {
            return "sellerdir/edit-weapon";
        }
        weapons = weaponRepository.findById(id).orElseThrow();
        weapons.setName(name);
        weapons.setPrice(price);


        weaponRepository.save(weapons);

        return "redirect:/sellerdir/detail/" + weapons.getId();
    }


    @GetMapping("/cheque")
    public String chequeAddView(Model model, Cheque cheque, Employee employee, Weapons weapon)
    {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employeeList", employees);
        Iterable<Weapons> weapons = weaponRepository.findAll();
        model.addAttribute("weaponList", weapons);
        return "sellerdir/cheque";
    }

    @PostMapping("/cheque")
    public String chequeAdd(@Valid Cheque cheque,
                            BindingResult bindingResult,
                            @RequestParam(name = "weapons") Long weapon_id, @RequestParam(name = "employee") Long employee_id,

//            @RequestParam String name,
//                         @RequestParam Integer year,
//                         @RequestParam String genre,
//                         @RequestParam String country,
//                            @RequestParam Integer budget,
                            Model model)
    {


        if(bindingResult.hasErrors())
        {
            Iterable<Employee> employees = employeeRepository.findAll();
            model.addAttribute("employeeList", employees);
            Iterable<Weapons> weapons = weaponRepository.findAll();
            model.addAttribute("weaponList", weapons);
            return "sellerdir/cheque";
        }

        Weapons weapons = weaponRepository.findById(weapon_id).orElseThrow();

        Employee employee = employeeRepository.findById(employee_id).orElseThrow();

        cheque.getWeapons().add(weapons);
        cheque.setEmployee(employee);

        chequeRepository.save(cheque);

        return "redirect:/sellerdir";
    }

}
