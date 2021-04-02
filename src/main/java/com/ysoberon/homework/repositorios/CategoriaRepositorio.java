package com.ysoberon.homework.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysoberon.homework.modelo.Categoria;

 

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

}
