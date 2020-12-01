package com.masivian.roulette.repository;

import org.springframework.data.repository.CrudRepository;

import com.masivian.roulette.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
