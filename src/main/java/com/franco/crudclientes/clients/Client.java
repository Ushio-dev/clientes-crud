package com.franco.crudclientes.clients;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private boolean isActive = true;
	
	public Client() {
		
	}
	
	public Client(Long idClient, String name, boolean isActive) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.isActive = isActive;
	}

	public Client(ClientBuilder builder) {
		this.name = builder.getName();
	}
	
	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	public static ClientBuilder builder() {
		return new ClientBuilder();
	}
}

//Builder

class ClientBuilder {
	private String nameBuilder;
		
	public ClientBuilder(){}
	
	public String getName() {
		return this.nameBuilder;
	}
	public ClientBuilder setName(String name) {
		this.nameBuilder = name;
		return this;
	}
		
	public Client build() {
		return new Client(this);
	}
}
