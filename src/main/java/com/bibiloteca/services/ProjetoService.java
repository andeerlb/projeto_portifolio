package com.bibiloteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibiloteca.enums.StatusProjeto;
import com.bibiloteca.models.Projeto;
import com.bibiloteca.repositories.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository repository;

	public Projeto save(Projeto projeto) {
		return repository.save(projeto);
	}

	public Projeto getOne(Integer id) {
		Optional<Projeto> op = repository.findById(id);
		return op.isPresent() ? op.get() : null;
	}

	public boolean delete(Integer id) {
		System.out.println(id);
		Optional<Projeto> op = repository.findById(id);
		op.ifPresent(p -> {

			if (StatusProjeto.Iniciado.equals(p.getStatus()) || StatusProjeto.Andamento.equals(p.getStatus())
					|| StatusProjeto.Encerrado.equals(p.getStatus())) {
				throw new RuntimeException(
						String.format("Não foi possível  excluir o projeto. Status: %s.", p.getStatus()));
			}

			repository.delete(p);
		});
		return op.isPresent() ? true : false;
	}
}
