package com.department.service;

import com.department.model.Department;
import com.department.model.Response;

public interface IDepartmentService {

    Response createDepartment(Department department);

    Response getDepartmentByEmployeeId(Long id);

    Response getDepartments();

}
