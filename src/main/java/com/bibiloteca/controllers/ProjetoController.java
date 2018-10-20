package com.bibiloteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bibiloteca.models.Projeto;
import com.bibiloteca.services.ProjetoService;

@RestController
@RequestMapping("api/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoService service;

	@PostMapping
	public ResponseEntity<Projeto> create(@RequestBody Projeto projeto) {
		return ResponseEntity.ok(service.save(projeto));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@RequestBody Projeto projeto, @PathVariable Integer id) {
		Projeto p = service.getOne(id);
		return ResponseEntity.ok(p != null ? service.save(p) : HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> read(@PathVariable Integer id) {
		Projeto p = service.getOne(id);
		return ResponseEntity.ok(p != null ? p : HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		return ResponseEntity.ok(service.delete(id));
	}
}
