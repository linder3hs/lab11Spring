package com.tecsup.gestion.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@WebAppConfiguration
public class EmployeeControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
    @Test
public void givenWac_whenServletContext_thenItProvidesGreetController() {
    ServletContext servletContext = wac.getServletContext();
     
    Assert.assertNotNull(servletContext);
    Assert.assertTrue(servletContext instanceof MockServletContext);
    Assert.assertNotNull(wac.getBean("employeeController"));
}
    
    @Test
	public void list() throws Exception {
		mockMvc.perform(get("/admin/emp/list"))
				.andExpect(status().isOk())
				.andExpect(view().name("admin/emp/list"))
				.andExpect(forwardedUrl("/WEB-INF/views/admin/emp/list.jsp"))
				.andExpect(model().attribute("employees", hasSize(3)))
				.andExpect(model().attribute("employees",
						hasItem(allOf(  
										hasProperty("employeeId", is(1)),
										hasProperty("login", is("lhassinger")),
										hasProperty("password", is("123")),
										hasProperty("firstname", is("Linder")),
										hasProperty("lastname", is("Hassinger")), 
										hasProperty("salary", is(6000)) //,
										//hasProperty("department",hasProperty("departmentId", is(12)))
									))));
	}
    
	@Test
    public void editForm() throws Exception {
		
        mockMvc.perform(get("/admin/emp/editform/100"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/emp/editform"))
                .andExpect(forwardedUrl("/WEB-INF/views/admin/emp/editform.jsp"))
                .andExpect(model().attribute("command", hasProperty("employeeId", is(1))))
                .andExpect(model().attribute("command", hasProperty("login", is("lhassinger"))))
                .andExpect(model().attribute("command", hasProperty("password", is("123"))))
                .andExpect(model().attribute("command", hasProperty("firstname", is("Linder"))))
                .andExpect(model().attribute("command", hasProperty("lastname", is("Hassinger"))))
                .andExpect(model().attribute("command", hasProperty("salary", is(6000))))
                //.andExpect(model().attribute("command", 
                //		hasProperty("department", hasProperty("departmentId",is(12)))))
            ;
	}
	
	@Test
	public void validarRegister() throws Exception {
		mockMvc.perform(get("/admin/emp/list"))
		.andExpect(model().attribute("employees", 
				hasItem(allOf(  
						hasProperty("employeeId", is(1)),
						hasProperty("login", is("lhassinger")),
						hasProperty("password", is("123")),
						hasProperty("firstname", is("Linder")),
						hasProperty("lastname", is("Hassinger")), 
						hasProperty("salary", is(6000)) //,
						//hasProperty("department",hasProperty("departmentId", is(12)))
					))))
		.andExpect(model().attribute("employees", 
				hasItem(allOf(  
						hasProperty("employeeId", is(4)),
						hasProperty("login", is("rhaurhua")),
						hasProperty("password", is("345")),
						hasProperty("firstname", is("Renatta")),
						hasProperty("lastname", is("Huarhua")), 
						hasProperty("salary", is(3000)) //,
						//hasProperty("department",hasProperty("departmentId", is(12)))
					))));
	}

}
