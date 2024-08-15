package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!
//add method to sort by lastname spring data jpa auto parse the method and look for patter and create appropriate query
    public List<Employee> findAllByOrderByLastNameAsc();
}
