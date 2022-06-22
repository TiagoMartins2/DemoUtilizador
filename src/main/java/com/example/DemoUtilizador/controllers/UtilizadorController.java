package com.example.DemoUtilizador.controllers;


import com.example.DemoUtilizador.model.Utilizador;
import com.example.DemoUtilizador.repository.ComputerRepository;
import com.example.DemoUtilizador.repository.UtilizadorRepository;
import com.example.DemoUtilizador.dto.UtilizadorRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilizador")
public class UtilizadorController {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private ComputerRepository computerRepository;


    //KEYCLOAK
    private String authServerUrl = "http://localhost:8080/auth";
    private String real = "springBootKeycloak";
    private String clientID = "login-app";
    private String role = "user";
    private String clientSecret = "3b5dd2d1-95c3-4bdb-bf8c-7bf980efc1ec";


    @PostMapping("/saveUser")
    public Utilizador criarUtilizador(@RequestBody UtilizadorRequest request){
        return utilizadorRepository.save(request.getUtilizador());
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
