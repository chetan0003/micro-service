package com.employee.controller;

import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.Response;
import com.employee.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final IEmployeeService employeeService;

    @PostMapping
    ResponseEntity<Response<Employee>> postEmployee(@RequestBody  Employee employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Response<Department>> getDepartmentByEmployeeId(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getDepartmentByEmployeeId(id),HttpStatus.OK);
    }

}
