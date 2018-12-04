package com.supinfo.rmt.services;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Worktime;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class WorktimeService {

	@PersistenceContext
	private EntityManager em;

	public Worktime save(Worktime worktime) {
		em.persist(worktime);
		return worktime;
	}

	public List<Worktime> getWorktimesByEmployee(Employee employee) {
		return em.createQuery("SELECT w FROM Worktime w WHERE w.employee=:employee", Worktime.class)
				 .setParameter("employee", employee)
				 .getResultList();
	}

	public void remove(Worktime worktime) {
		em.remove(em.merge(worktime));
	}

}
