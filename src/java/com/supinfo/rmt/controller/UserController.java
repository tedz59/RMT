package com.supinfo.rmt.controller;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.User;
import com.supinfo.rmt.services.UserService;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author Binarymachine
 */
@ManagedBean
@SessionScoped
public class UserController {

	@EJB
	private UserService userService;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	private User loggedUser;

	public String login() {
		loggedUser = userService.login(username, password)
								.orElse(null);

		if (loggedUser instanceof Manager) {
			return "manager_home";
		} else if (loggedUser instanceof Employee) {
			return "employee_home";
		} else {
			return null;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
}