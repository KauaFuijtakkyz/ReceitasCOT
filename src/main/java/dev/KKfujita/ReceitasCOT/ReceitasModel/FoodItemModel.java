package dev.KKfujita.ReceitasCOT.ReceitasModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Receitas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer quantidade;
    private LocalDate validade;
    private Categorias categorias;



}
