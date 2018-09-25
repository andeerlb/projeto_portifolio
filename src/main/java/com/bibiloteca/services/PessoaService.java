package com.bibiloteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibiloteca.models.Pessoa;
import com.bibiloteca.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Pessoa save(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	public Pessoa getOne(Integer id) {
		Optional<Pessoa> op = repository.findById(id);
		return op.isPresent() ? op.get() : null;
	}

	public boolean delete(Integer id) {
		Optional<Pessoa> op = repository.findById(id);
		op.ifPresent(p -> {
			repository.delete(p);
		});
		return op.isPresent() ? true : false;
	}
}
