package com.supinfo.rmt.controller;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.services.EmployeeService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.ListDataModel;
import java.util.List;

@ManagedBean
public class EmployeeController {

	@EJB
	private EmployeeService employeeService;

	@ManagedProperty("#{userController}")
	private UserController userController;

	private Employee employee = new Employee();

	private ListDataModel<Employee> dataModel;

	public String create() {
		this.employee.setManager((Manager) userController.getLoggedUser());

		if (employeeService.save(this.employee)
						   .isPresent()) {
			return "manager_home?faces-redirect=true";
		}

		return null;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ListDataModel<Employee> getDataModel() {
		if (null == dataModel) {
			List<Employee> employees = employeeService.getByManager((Manager) userController
																				  .getLoggedUser());
			dataModel = new ListDataModel<>(employees);
		}
		return dataModel;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
