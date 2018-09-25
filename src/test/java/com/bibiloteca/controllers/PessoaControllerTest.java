package com.bibiloteca.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bibiloteca.models.Pessoa;
import com.bibiloteca.services.PessoaService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PessoaService service;

	@Test
	public void deveDelegarAoServiceQuandoBuscarPessoa() throws Exception {
		Pessoa p = new Pessoa();
		p.setId(1);

		when(service.getOne(1)).thenReturn(p);

		mvc.perform(get("/api/pessoa/{id}", p.getId())).andExpect(status().isOk());

		verify(service).getOne(p.getId());
	}

	@Test
	public void deveDelegarAoServiceQuandoAtualizarPessoa() throws Exception {
		Pessoa p = new Pessoa();
		p.setId(1);
		String json = new Gson().toJson(p);

		when(service.getOne(p.getId())).thenReturn(p);

		mvc.perform(put("/api/pessoa/{id}", p.getId()).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());

		verify(service).getOne(p.getId());
		verify(service).save(p);
	}
}
