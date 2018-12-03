package com.supinfo.rmt.controller;

import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Tag("Employee Controller Tests")
class EmployeeControllerTest {

	private EmployeeController employeeController;

	private Manager loggedManager = new Manager();

	@Mock
	private EmployeeService employeeServiceMocked;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		employeeController = new EmployeeController();
		employeeController.setEmployeeService(employeeServiceMocked);

		UserController userController = new UserController();
		userController.setLoggedUser(loggedManager);
		employeeController.setUserController(userController);

		when(employeeServiceMocked.save(this.employeeController.getEmployee())).thenReturn(Optional.of(this.employeeController.getEmployee()));
	}

	@DisplayName("If i create successully an user, the view is returned.")
	@Test
	void shouldReturnTheViewAfterCreateUser() {
		String view = employeeController.create();

		assertThat(view).isNotNull();
		assertThat(view).isEqualTo("manager_home?faces-redirect=true");
	}

	@DisplayName("If i try to create an already existe user, no view is returned.")
	@Test
	void shouldReturnNullIfUserAlReadyExist() {

		when(employeeServiceMocked.save(this.employeeController.getEmployee())).thenReturn(Optional.empty());

		String view = employeeController.create();

		assertThat(view).isNull();
	}
}