package com.bibiloteca.services;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bibiloteca.enums.StatusProjeto;
import com.bibiloteca.models.Projeto;
import com.bibiloteca.repositories.ProjetoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProjetoServiceTest {

	@InjectMocks
	private ProjetoService service;

	@Mock
	private ProjetoRepository repository;

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Test
	public void deveLancarExcecaoQuandoDeletarProjetoComStatusIniciadoLancamentoOuEncerrado() {
		ex.expect(Exception.class);
		Projeto p = createProjeto();

		when(repository.findById(p.getId())).thenReturn(Optional.of(p));

		service.delete(p.getId());
	}

	private Projeto createProjeto() {
		Projeto p = new Projeto();
		p.setId(1);
		p.setStatus(StatusProjeto.Andamento);
		return p;
	}
}
