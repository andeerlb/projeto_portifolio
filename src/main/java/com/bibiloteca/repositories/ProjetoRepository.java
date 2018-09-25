package com.bibiloteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibiloteca.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
}
