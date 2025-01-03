package com.msgsender.msg;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;


@Data
@EqualsAndHashCode
@Entity
@Table(name = "Contatos")
public class Contatos {
    
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String nome;

@Column(length = 11) 
private String telefone;

}
