package com.supinfo.rmt.controller;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.User;
import com.supinfo.rmt.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Tag("User controller test classe.")
class UserControllerTest {


	UserController userController;

	@Mock
	private UserService userServiceMocked;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.initMocks(this);
		userController = new UserController();
		userController.setUserService(userServiceMocked);


	}

	@DisplayName("When i get an employee, i return the employee action.")
	@Test
	void shouldReturnEmployeeAction() {

		mockReturnEmployee();

		String action = userController.login();

		assertThat(action).isNotNull();
		assertThat(action).isEqualTo("employee_home");

	}

	private void mockReturnEmployee() {
		userController.setUsername("employee");
		userController.setPassword("employee");

		Optional<User> employee = Optional.of(new Employee());
		when(userServiceMocked.login("employee", "employee")).thenReturn(employee);
	}

	@DisplayName("When i get a Manager, i return the employee action.")
	@Test
	void shouldReturnManagerAction() {

		userController.setUsername("manager");
		userController.setPassword("manager");

		Optional<User> manager = Optional.of(new Manager());
		when(userServiceMocked.login("manager", "manager")).thenReturn(manager);


		String action = userController.login();

		assertThat(action).isNotNull();
		assertThat(action).isEqualTo("manager_home");

	}

	@DisplayName("When i called wrong login and password, no view is returned.")
	@Test
	void shouldReturnNullIfLoginFailed() {
		userController.setUsername("other");
		userController.setPassword("other");

		when(userServiceMocked.login("other", "other")).thenReturn(Optional.empty());

		String action = userController.login();

		assertThat(action).isNull();
	}
}