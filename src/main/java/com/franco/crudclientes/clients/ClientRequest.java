package com.franco.crudclientes.clients;

public class ClientRequest {
	private String clientName;

	public ClientRequest() {
		
	}
	public ClientRequest(String clientName) {
		super();
		this.clientName = clientName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
}
