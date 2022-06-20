package com.example.DemoUtilizador.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "Utilizador")
public class Utilizador {

    @Id
    @GeneratedValue
    private int bi;

    private String nome;
    private String username;
    private String password;

    @OneToMany(targetEntity = Computer.class,cascade = CascadeType.ALL)
    @JoinColumn(name= "uc_fk", referencedColumnName = "bi")
    private List<Computer> computers;
}
