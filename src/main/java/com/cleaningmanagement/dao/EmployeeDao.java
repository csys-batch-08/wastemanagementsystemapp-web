package com.cleaningmanagement.dao;


import java.util.List;

import com.cleaningmanagement.model.Employee;

public interface EmployeeDao {
	public boolean insertEmpDatabase(Employee emp);
	public Employee validation(String email, String password);
	public int findEmpId(Employee employee);
	public Employee findEmployee(String location);
	public List<Employee> showEmployee(Employee emp);
	public List<List<Object>> findEmployeeRequest(Employee employee);
	public boolean updateEmployeeStatus(String status,int reqId);
}
