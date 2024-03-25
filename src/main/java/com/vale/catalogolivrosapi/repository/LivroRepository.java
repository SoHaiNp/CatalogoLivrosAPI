package com.vale.catalogolivrosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vale.catalogolivrosapi.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
