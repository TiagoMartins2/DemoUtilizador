package com.example.DemoUtilizador.controllers;


import com.example.DemoUtilizador.configuration.DomainBeans;
import com.example.DemoUtilizador.model.Utilizador;
import com.example.DemoUtilizador.repository.ComputerRepository;
import com.example.DemoUtilizador.repository.UtilizadorRepository;
import com.example.DemoUtilizador.service.KeycloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
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


    @PostMapping
    public String criarUtilizador(@RequestBody Utilizador user){
        utilizadorRepository.save(user);
        try{
            keycloakService.addUser(user);
            return "User adicionado na base de dados com sucesso";
        }
        catch(Exception e){
            e.printStackTrace();
            return "Algo deu mal ao inserir o utilizador na base de dados " + e;
        }
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




    @GetMapping("/get{username}")
    public List<Utilizador> getUser(@RequestParam(value = "username") String username){
        return utilizadorRepository.findByUsername(username);
    }
}
