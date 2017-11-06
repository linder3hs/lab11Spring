package com.tecsup.gestion.services;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.LoginException;
import com.tecsup.gestion.model.Employee;

public interface SecurityService {

	Employee validate(String login, String pwd) throws LoginException, DAOException;
	
	

}
