package com.department.service.impl;

import com.department.domain.DepartmentDetail;
import com.department.mapper.DepartmentMapper;
import com.department.model.Department;
import com.department.model.Response;
import com.department.repository.DepartmentRepository;
import com.department.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {
    
    private final DepartmentRepository departmentRepository;
    @Override
    public Response createDepartment(Department department) {
        DepartmentDetail entity = DepartmentMapper.mapDepartmentToEntity.apply(department);
        DepartmentDetail save = departmentRepository.save(entity);
        Department apply = DepartmentMapper.mapEntityToDepartment.apply(save);
        return Response.builder().statusCode(200).data(apply).build();
    }

    @Override
    public Response getDepartmentByEmployeeId(Long id) {
        DepartmentDetail byEmployeeId = departmentRepository.findByEmployeeId(id);
        Department apply = DepartmentMapper.mapEntityToDepartment.apply(byEmployeeId);
        return Response.builder().statusCode(200).data(apply).build();
    }

    @Override
    public Response getDepartments() {
        List<DepartmentDetail> all = departmentRepository.findAll();
        List<Department> collect = all.stream().map(m -> DepartmentMapper.mapEntityToDepartment.apply(m)).collect(Collectors.toList());
        return Response.builder().statusCode(200).data(collect).build();
    }
}
