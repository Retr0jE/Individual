package ru.spring.ammu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.ammu.Models.*;
import ru.spring.ammu.Repositories.QuantityRepository;
import ru.spring.ammu.Repositories.SupplierRepository;
import ru.spring.ammu.Repositories.WarehouseRepository;
import ru.spring.ammu.Repositories.WeaponRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/storekeeperdir")
@PreAuthorize("hasAnyAuthority('STOREKEEPER')")
public class StoreController {

    @Autowired
    WeaponRepository weaponRepository;

    @Autowired
    QuantityRepository quantityRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("")
    public String storekeeper(Model model)
    {
        Iterable<Warehouse> listWarehouse = warehouseRepository.findAll();
        model.addAttribute("listWarehouse",listWarehouse);
        Iterable<Supplier> listSupplier = supplierRepository.findAll();
        model.addAttribute("listSupplier",listSupplier);
        return "storekeeperdir/storekeeper";
    }
    @GetMapping("/cogquantity")
    public String chequeView(Model model)
    {

        Iterable<Quantity> quantities = quantityRepository.findAll();


        model.addAttribute("quantity", quantities);
        return "/storekeeperdir/cogquantity";
    }

    @GetMapping("/addsupplier")
    public String supplierAddView(Model model, Supplier supplier)
    {
        return "storekeeperdir/add-supplier";
    }

    @PostMapping("/addsupplier")
    public String supplierAdd(@Valid Supplier supplier,
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

            return "storekeeperdir/add-supplier";
        }

        supplierRepository.save(supplier);

        return "redirect:/storekeeperdir";
    }

    @GetMapping("/addwarehouse")
    public String warehouseAddView(Model model, Supplier supplier, Warehouse warehouse)
    {
        Iterable<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("supplierList", suppliers);
        return "storekeeperdir/add-warehouse";
    }

    @PostMapping("/addwarehouse")
    public String warehouseAdd(@Valid Warehouse warehouse,
                            BindingResult bindingResult,
                            @RequestParam(name = "supplier") Long supplier_id,

//            @RequestParam String name,
//                         @RequestParam Integer year,
//                         @RequestParam String genre,
//                         @RequestParam String country,
//                            @RequestParam Integer budget,
                            Model model)
    {

        if(bindingResult.hasErrors())
        {
            Iterable<Supplier> suppliers = supplierRepository.findAll();
            model.addAttribute("supplierList", suppliers);
            return "storekeeperdir/add-warehouse";
        }

        Supplier supplier = supplierRepository.findById(supplier_id).orElseThrow();

        warehouse.setSupplier(supplier);

        warehouseRepository.save(warehouse);

        return "redirect:/storekeeperdir";
    }

    @GetMapping("/filter")
    public String Filter(@RequestParam(name = "searchName") String name,
                               Model model)
    {
        List<Warehouse> warehouses = warehouseRepository.findByAddressContaining(name);
        model.addAttribute("warehouse", warehouses);
        List<Supplier> suppliers = supplierRepository.findByNameContaining(name);
        model.addAttribute("supplier", suppliers);

        return "storekeeperdir/filter";
    }

    @GetMapping("/del/{id}")
    public String warehouseDel(@PathVariable Long id)
    {
        warehouseRepository.deleteById(id);
        return "redirect:/storekeeperdir";
    }
    @GetMapping("/del2/{id}")
    public String supplierDel(@PathVariable Long id)
    {
        supplierRepository.deleteById(id);
        return "redirect:/storekeeperdir";
    }

    @GetMapping("/edit2/{id}")
    public String supplierEditView(@PathVariable Long id, Model model, Supplier supplier)
    {
        supplier = supplierRepository.findById(id).orElseThrow();
        model.addAttribute("supplier", supplier);
        return "/storekeeperdir/edit-supplier";
    }


    @PostMapping("/edit2/{id}")
    public String supplierEdit(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String phone,
                              Model model, @Valid Supplier supplier, BindingResult bindingResult
    )
    {


        if(bindingResult.hasErrors())
        {
            return "storekeeperdir/edit-supplier";
        }
        supplier = supplierRepository.findById(id).orElseThrow();
        supplier.setName(name);
        supplier.setPhone(phone);


        supplierRepository.save(supplier);

        return "redirect:/storekeeperdir/";
    }

    @GetMapping("/edit/{id}")
    public String warehouseEditView(@PathVariable Long id, Model model, Supplier supplier, Warehouse warehouse)
    {
        warehouse = warehouseRepository.findById(id).orElseThrow();
        model.addAttribute("warehouse", warehouse);
        Iterable<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("supplierList", suppliers);
        return "/storekeeperdir/edit-warehouse";
    }


    @PostMapping("/edit/{id}")
    public String warehouseEdit(@PathVariable Long id,
                               @RequestParam String address,

                               Model model, @Valid Warehouse warehouse, BindingResult bindingResult,
                                @RequestParam(name = "supplier") Long supplier_id

    )
    {
        if(bindingResult.hasErrors())
        {
            Iterable<Supplier> suppliers = supplierRepository.findAll();
            model.addAttribute("supplierList", suppliers);
            return "storekeeperdir/edit-warehouse";
        }
        warehouse = warehouseRepository.findById(id).orElseThrow();
        warehouse.setAddress(address);
        Supplier supplier = supplierRepository.findById(supplier_id).orElseThrow();
        warehouse.setSupplier(supplier);
        warehouseRepository.save(warehouse);
        return "redirect:/storekeeperdir/";
    }

    @GetMapping("/quantity")
    public String quantityAddView(Model model, Quantity quantity)
    {
        Iterable<Weapons> weapons = weaponRepository.findAll();
        model.addAttribute("weaponList", weapons);
        Iterable<Warehouse> warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouseList", warehouses);
        return "storekeeperdir/quantity";
    }

    @PostMapping("/quantity")
    public String quantityAdd(@Valid Quantity quantity,
                               BindingResult bindingResult,
                               @RequestParam(name = "warehouse") Long warehouse_id,
                              @RequestParam(name = "weapon") Long weapon_id,

//            @RequestParam String name,
//                         @RequestParam Integer year,
//                         @RequestParam String genre,
//                         @RequestParam String country,
//                            @RequestParam Integer budget,
                               Model model)
    {

        if(bindingResult.hasErrors())
        {
            Iterable<Weapons> weapons = weaponRepository.findAll();
            model.addAttribute("weaponList", weapons);
            Iterable<Warehouse> warehouses = warehouseRepository.findAll();
            model.addAttribute("warehouseList", warehouses);
            return "storekeeperdir/quantity";
        }

        Warehouse warehouse = warehouseRepository.findById(warehouse_id).orElseThrow();
        Weapons weapons = weaponRepository.findById(weapon_id).orElseThrow();

        quantity.setWarehouse(warehouse);
        quantity.setWeapons(weapons);

        quantityRepository.save(quantity);

        return "redirect:/storekeeperdir";
    }

}
