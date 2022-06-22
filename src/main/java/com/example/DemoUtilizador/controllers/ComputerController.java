package com.example.DemoUtilizador.controllers;

import com.example.DemoUtilizador.model.Computer;
import com.example.DemoUtilizador.repository.ComputerRepository;
import com.example.DemoUtilizador.repository.UtilizadorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/computer")
@EnableScheduling
public class ComputerController {

    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private UtilizadorRepository utilizadorRepository;


    @GetMapping("/getRecent")
    public List<Computer> getRepoComputer(){
        return computerRepository.getRecentComputer();
    }


    @Scheduled(fixedRate = 1000*5)
    void findByLocalDateBetweenMethod(){

        //start date
        LocalDateTime startDateTime = LocalDateTime.now().minusHours(1);

        List<Computer> computers = computerRepository.findByLocalDateAfter(startDateTime);

        computers.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getModelo());
        });
    }


    @PostMapping("/saveComputer")
    public String saveComputer(@RequestBody Computer computer){
        try{
            computerRepository.save(computer);
            return "Computador guardado com sucesso";
        }catch (Exception e){
            return "Não foi possivel guardar computador";
        }
    }



    @GetMapping("/getAll")
    public List<Computer> getAllComputer(){
        try{
            return computerRepository.findAll();
        }catch (Exception e){
            List<Computer> listavazia = new ArrayList<>();
            return listavazia;
        }
    }


    @GetMapping("/getbyID/{id}")
    public Optional<Computer> getComputerbyBI(@PathVariable Integer id){
        return computerRepository.findById(id);
    }



    @PutMapping("/editID/{id}")
    public Computer putComputerbyID(@PathVariable Integer id, @RequestBody Computer computerr){
        Computer computerAtual = computerRepository.findById(id).get();
        BeanUtils.copyProperties(computerr, computerAtual, "id");
        return computerRepository.save(computerAtual);
    }


    @DeleteMapping("/deletebyID/{id}")
    public String deleteComputer(@PathVariable Integer id){
        try{
            computerRepository.deleteById(id);
            return "Delete feito com sucesso";
        }catch (Exception e){
            return "Delete feito sem sucesso";
        }
    }




}
