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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bi;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lasname;

    @OneToMany(targetEntity = Computer.class,cascade = CascadeType.ALL)
    @JoinColumn(name= "uc_fk", referencedColumnName = "bi")
    private List<Computer> computers;


}
