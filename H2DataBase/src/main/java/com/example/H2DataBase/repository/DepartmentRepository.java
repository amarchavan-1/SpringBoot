package com.example.H2DataBase.repository;

import com.example.H2DataBase.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository
        extends CrudRepository<Department, Long> {
}