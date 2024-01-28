package com.franco.crudclientes.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franco.crudclientes.clients.exceptions.ClientNotFound;

@RestController
@RequestMapping("/")
public class ClientsController {
	private final ClientsRepository clientsRepository;
	
	public ClientsController(ClientsRepository repository) {
		this.clientsRepository = repository;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Client>> findAllClients() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(clientsRepository.findAll());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findClient(@PathVariable(name = "id") Long idClient) {
		try {
			Optional<Client> client = clientsRepository.findById(idClient);
			if (client.isEmpty()) {
				throw new ClientNotFound("no se encontro cliente");
			}
			return ResponseEntity.status(HttpStatus.OK).body(client.get());
		}catch (ClientNotFound e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<Client> saveNewClient(@RequestBody ClientRequest clientRequest) {
		try {
			Client client = Client.builder()
					.setName(clientRequest.getClientName())
					.build();
			return ResponseEntity.status(HttpStatus.CREATED).body(clientsRepository.save(client));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateClient(
			@PathVariable(name = "id") Long idClient,
			@RequestBody ClientRequest clientRequest) {
		try {
			Optional<Client> client = clientsRepository.findById(idClient);
			
			if (client.isEmpty()) throw new ClientNotFound("No se encontro cliente");
			
			client.get().setName(clientRequest.getClientName());
			
			return ResponseEntity.status(HttpStatus.OK).body(client.get());
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(name = "id") Long idClient){ 
		try {
			Optional<Client> client = clientsRepository.findById(idClient);
			if (client.isEmpty()) {
				throw new ClientNotFound("No se encontro cliente");
			}
			clientsRepository.delete(client.get());
			
			return ResponseEntity.status(HttpStatus.OK).body(client.get());
		}catch (ClientNotFound e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
}
