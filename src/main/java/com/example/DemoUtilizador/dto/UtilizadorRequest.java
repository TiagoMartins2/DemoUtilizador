package com.example.DemoUtilizador.dto;


import com.example.DemoUtilizador.model.Utilizador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilizadorRequest {

    private Utilizador utilizador;

}
