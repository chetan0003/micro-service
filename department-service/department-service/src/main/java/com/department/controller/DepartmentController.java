package com.department.controller;

import com.department.model.Department;
import com.department.model.Response;
import com.department.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/departments")
@RestController
public class DepartmentController {

    private final IDepartmentService departmentService;

    @PostMapping
    ResponseEntity<Response<Department>> postEmployee(@RequestBody  Department department) {
        return new ResponseEntity<>(departmentService.createDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<Response<Department>> getDepartmentByEmployeeId(@RequestParam Long id) {
        return new ResponseEntity<>(departmentService.getDepartmentByEmployeeId(id), HttpStatus.CREATED);
    }

//    @GetMapping
//    ResponseEntity<Response<Department>> getDepartments() {
//        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.CREATED);
//    }

}
