package com.employee.feign.client;

import com.employee.model.Department;
import com.employee.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DEPARTMENT-SERVICE", path = "/departments")
public interface DepartmentClient {

    @PostMapping
    Response postDepartment(Department department);
    @GetMapping
    Response getDepartmentByEmployeeId(@RequestParam  Long id);

    @GetMapping
    Response getDepartments();

}
