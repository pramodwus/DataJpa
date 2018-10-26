package com.app.dao;

import java.util.List;

import com.app.entity.Employee;

public interface EmpDao 
{
public boolean saveEmployee(Employee emp);
public boolean updateEmployee(Employee emp);
public boolean deleteEmployee(int empId);
public Employee getEmployeeById(int empId);
public List<Employee> getAllEmployees();
}