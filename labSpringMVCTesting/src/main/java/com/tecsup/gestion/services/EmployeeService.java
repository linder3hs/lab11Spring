package com.tecsup.gestion.services;

import java.util.List;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.EmptyResultException;
import com.tecsup.gestion.mapper.EmployeeMapper;
import com.tecsup.gestion.model.Employee;

public interface EmployeeService {
	
	Employee find(int employee_id) throws DAOException, EmptyResultException;

	List <Employee> findAll() throws DAOException, EmptyResultException;
	
	void update(String login, String password, String lastname, String firstname, int salary, int dptId)
			throws DAOException;
	
	//List <Employee> findEmployeesDates(String name, String lastname, String salary) throws DAOException, EmptyResultException;

	
	//List <Employee> fin
	

}
