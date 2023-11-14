package com.department.mapper;

import com.department.domain.DepartmentDetail;
import com.department.model.Department;

import java.util.function.Function;

public interface DepartmentMapper {

    Function<Department, DepartmentDetail> mapDepartmentToEntity = department ->
            DepartmentDetail.builder()
                    .name(department.getName())
                    .employeeId(department.getEmployeeId())
                    .build();

    Function<DepartmentDetail, Department> mapEntityToDepartment = department ->
            Department.builder()
                    .id(department.getId())
                    .name(department.getName())
                    .employeeId(department.getEmployeeId())
                    .build();
}
