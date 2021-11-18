
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
@Table(name = "genero")
@Inheritance(strategy = InheritanceType.JOINED)
public class Genero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id_genero;
    
    private String nombre;
    
    @OneToOne
    private Imagen imagen;
    
    @ManyToOne
    private Pelicula pelicula;
}
