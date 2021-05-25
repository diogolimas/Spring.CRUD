package com.algaworks.crm.model;


public class ClientNotFoundException extends RuntimeException{

	public ClientNotFoundException(Long id)
	{
		super("Could not find client " + id);
	}
}
