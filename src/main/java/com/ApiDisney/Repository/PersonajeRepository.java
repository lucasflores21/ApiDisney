package com.ApiDisney.Repository;

import com.ApiDisney.Entity.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    
    //para buscar por nombre : /characters?name=nombre
    @Query("SELECT p FROM Personaje p where p.nombre like :name")
    List<Personaje> findByName(@Param("name") String name);

    
    //para buscar por edad : /characters?age=edad
    @Query("SELECT p FROM Personaje p where p.edad = :age")
    public List<Personaje> findByAge(@Param("age") Integer age);
    
    
    //para buscar por edad : /characters?movies=idMovie
     @Query("SELECT p from Personaje p where pelicula.id_pelicula = :idMovie")
    List<Personaje> findByPeli(@Param("idMovie") Integer idMovie);
}
