package com.example.service;

import java.util.List;

import com.example.beans.Employee;
import com.example.exceptions.ProfileNotFoundException;

public interface EmployeeService {
	public Employee createProfile(Employee employee);
	public Employee getProfile(String emailId);
	public Employee login(String emailId, String password) throws ProfileNotFoundException;

}
