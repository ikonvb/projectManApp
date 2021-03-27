package com.konstantinbulygin.pmwebapp.controllers;

import com.konstantinbulygin.pmwebapp.dao.UserAccountRepository;
import com.konstantinbulygin.pmwebapp.entities.Employee;
import com.konstantinbulygin.pmwebapp.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, UserAccountRepository userAccountRepository) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String displayEmployees(Model model) {

        //querying the database for employees
        List<Employee> employees = employeeService.getAll();

        //sending the data of employees to home view
        model.addAttribute("employeesList", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new-employee";
    }

    //saving project to DB
    @PostMapping("/save")
    public String saveEmployee(Model model, @Valid Employee employee, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "employees/new-employee";
        }

        redirectAttributes.addFlashAttribute("message", "Employee added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        employeeService.save(employee);
        return "redirect:/employees/new";
    }



    @GetMapping("/edit/{id}")
    public String displayEditEmployeeForm(@PathVariable long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/new-employee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}

























