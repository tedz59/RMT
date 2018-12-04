package com.supinfo.rmt.services;

import com.supinfo.rmt.entity.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class ClientService {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Save a client in the database.
	 *
	 * @param client
	 * 	to save.
	 *
	 * @return The client persisted.
	 */
	public Optional<Client> save(Client client) {
		try {
			em.createQuery("SELECT c FROM Client c WHERE c.name = :name", Client.class)
			  .setParameter("name", client.getName())
			  .getSingleResult();

			return Optional.empty();
		} catch (NoResultException e) {
			em.persist(client);
			return Optional.of(client);
		}
	}

	/**
	 * Retrieve all the client.
	 *
	 * @return a list of Client.
	 */
	public List<Client> getAll() {
		return em.createQuery("SELECT c FROM Client c", Client.class)
				 .getResultList();
	}

	/**
	 * Retrive a client by his id.
	 *
	 * @param id
	 * 	of the client.
	 *
	 * @return the Client.
	 */
	public Optional<Client> findById(Long id) {
		return Optional.ofNullable(em.find(Client.class, id));
	}

}
