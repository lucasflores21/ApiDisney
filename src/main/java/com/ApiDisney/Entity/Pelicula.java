package com.ApiDisney.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "pelicula")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id_pelicula;

    @OneToOne
    private Imagen imagen;

    private String titulo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date fecha_creacion;

    private Integer calificacion;

    @OneToOne
    private Genero genero;
}
