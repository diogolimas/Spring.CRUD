package com.algaworks.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.crm.model.Client;
import com.algaworks.crm.model.ClientNotFoundException;
import com.algaworks.crm.repository.ClientRepository;

import lombok.Data;

@RestController
@RequestMapping("/clients")
@Data
public class ClientController {
	@Autowired
	private ClientRepository clientRepository;
	
	
	@GetMapping
	public List<Client> all()
	{
		return clientRepository.findAll();
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client newClient(@RequestBody Client client)
	{
		return clientRepository.save(client);
	}
	
	@GetMapping("/{id}")
	public Client one(@PathVariable Long id)
	{
		return clientRepository.findById(id)
				.orElseThrow(() -> new ClientNotFoundException(id)) ;
		
	}
	
	@PutMapping("/{id}")
	public Client replaceClient(@RequestBody Client newClient, @PathVariable Long id)
	{
		return clientRepository.findById(id)
				.map(client -> {
					client.setNome(newClient.getNome());
					return clientRepository.save(client);
				})
				.orElseGet(() -> {
					newClient.setId(id);
					return clientRepository.save(newClient);
				});	
	}
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Long id)
	{
		clientRepository.deleteById(id);
	}
	
}
