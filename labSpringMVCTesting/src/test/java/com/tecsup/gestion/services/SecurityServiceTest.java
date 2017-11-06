package com.tecsup.gestion.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tecsup.gestion.exception.DAOException;
import com.tecsup.gestion.exception.LoginException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@WebAppConfiguration
public class SecurityServiceTest {

	@Autowired
	private SecurityService securityService;

	@Test
	public void testValidate() {

		try {
			//
			securityService.validate("jgomez", "123456");			
		} catch (LoginException e) {
			fail(e.getMessage());
		} catch (DAOException e) {
			fail(e.getMessage());
		}

	}

}
