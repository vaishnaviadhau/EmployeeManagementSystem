package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private EmployeeService employeeService;


    public EmployeeController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }
//add mapping for listing the employees

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        List<Employee> theEmployees=employeeService.findAll();
        theModel.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
//        create model attribute to bind form data
        Employee theEmployee=new Employee();
        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employee
        employeeService.save(theEmployee);
        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateTheEmployee(@RequestParam("employeeId") int theId, Model theModel){

        //get the employee from the service
        Employee theEmployee=employeeService.findById(theId);
        //  set employee as model attribute tp prepopulate the form
        theModel.addAttribute("employee",theEmployee);
        return "employees/employee-form";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId){

employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }

}
