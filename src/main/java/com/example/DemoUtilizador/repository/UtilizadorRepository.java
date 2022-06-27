package com.example.DemoUtilizador.repository;

import com.example.DemoUtilizador.model.Utilizador;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {

    List<Utilizador> findByUsername(@Param(value = "role") String username);

}
