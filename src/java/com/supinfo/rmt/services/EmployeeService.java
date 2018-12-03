package com.supinfo.rmt.services;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class EmployeeService {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Lead to save an employee in database.
	 * @param employee the employee to persist.
	 * @return the employee persisted.
	 */
	public Optional<Employee> save(Employee employee) {

		try {
			em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class)
			  .setParameter("username", employee.getUsername())
			  .getSingleResult();
		} catch (NoResultException e) {
			em.persist(employee);
			return Optional.of(employee);
		}

		return Optional.empty();

	}

	/**
	 * Retrieve all the employees by the manager username.
	 * @param manager Manager.
	 * @return a list of Employees.
	 */
	public List<Employee> getByManager(Manager manager) {
		return em.createQuery("SELECT e FROM Employee e WHERE e.manager = :manager", Employee.class)
			.setParameter("manager", manager)
			.getResultList();
	}


}
