package com.example.DemoUtilizador.controllers;


import com.example.DemoUtilizador.configuration.DomainBeans;
import com.example.DemoUtilizador.model.Utilizador;
import com.example.DemoUtilizador.repository.ComputerRepository;
import com.example.DemoUtilizador.repository.UtilizadorRepository;
import com.example.DemoUtilizador.dto.UtilizadorRequest;
import com.example.DemoUtilizador.service.KeycloakService;
import jdk.jshell.execution.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilizador")
public class UtilizadorController {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private DomainBeans domainBeans;

    public UtilizadorController(KeycloakService keyService, DomainBeans dbeans){
        this.domainBeans = dbeans;
        this.keycloakService = keyService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> criarUtilizador(@RequestBody Utilizador user){
        utilizadorRepository.save(user);
        Response createdResponse = keycloakService.createKeycloakUser(user);
        return ResponseEntity.status(createdResponse.getStatus()).build();
    }


    @GetMapping("/getAll")
    public List<Utilizador> findAllUsers(){
        return utilizadorRepository.findAll();
    }


    @GetMapping("/getbyBI/{bi}")
    public Optional<Utilizador> getUtilizadorbyBI(@PathVariable Integer bi){
        return utilizadorRepository.findById(bi);
    }


    @PutMapping("/editbyID/{bi}")
    public Utilizador putUtilizadorbyBI(@PathVariable Integer bi, @RequestBody Utilizador user){
        Utilizador userAtual = utilizadorRepository.findById(bi).get();
        BeanUtils.copyProperties(user, userAtual, "bi");
        return utilizadorRepository.save(userAtual);
    }


    @DeleteMapping("/deletebyBI/{bi}")
    public String deleteUtilizador(@PathVariable Integer bi){
        try{
            utilizadorRepository.deleteById(bi);
            return "Delete feito com sucesso";
        }catch (Exception e){
            return "Delete feito sem sucesso";
        }
    }
}
