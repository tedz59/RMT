package com.supinfo.rmt.controller;

import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.services.ClientService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ClientController {

	@EJB
	private ClientService clientService;

	private Client client = new Client();

	private List<SelectItem> selectItems;

	public String create() {
		clientService.save(client);
		return "manager_home?faces-redirect=true";
	}

	public List<SelectItem> getSelectItems() {
		if(null == selectItems) {
			selectItems = new ArrayList<>();
			for(Client client : clientService.getAll()) {
				selectItems.add(new SelectItem(client, client.getName()));
			}
		}

		return selectItems;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
