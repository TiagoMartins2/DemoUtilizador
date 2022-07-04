package com.example.DemoUtilizador.controllers;


import com.example.DemoUtilizador.configuration.DomainBeans;
import com.example.DemoUtilizador.model.Utilizador;
import com.example.DemoUtilizador.repository.ComputerRepository;
import com.example.DemoUtilizador.repository.UtilizadorRepository;
import com.example.DemoUtilizador.service.KeycloakService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilizadores")
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



    @PostMapping(value = "/addUsers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String criarUtilizador(@RequestBody Utilizador user) {
        this.utilizadorRepository.save(user);
        try {
            this.keycloakService.addUser(user);
            return "Sucesso";
        }catch(Exception e){
            return "Erro";
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
    public void deleteUtilizador(@PathVariable Integer bi){
            utilizadorRepository.deleteById(bi);
    }

    @GetMapping("/get{username}")
    public List<Utilizador> getUser(@RequestParam(value = "username") String username){
        return utilizadorRepository.findByUsername(username);
    }
}
