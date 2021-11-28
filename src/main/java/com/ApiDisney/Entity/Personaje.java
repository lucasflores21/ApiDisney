
package com.ApiDisney.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "personaje")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_personaje;
    
    @OneToOne
    private Imagen imagen;
    
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    
    @ManyToOne
    private Pelicula pelicula;
            
    
}
