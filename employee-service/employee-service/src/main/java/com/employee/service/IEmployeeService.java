package com.employee.service;

import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.Response;

public interface IEmployeeService {

    Response createEmployee(Employee employee);

    Response getDepartmentByEmployeeId(Long employeeId);
}
