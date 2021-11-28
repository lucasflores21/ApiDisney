
package com.ApiDisney.Repository;

import com.ApiDisney.Entity.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{
    @Query("SELECT p FROM Pelicula p where p.titulo like :nombre")
    List<Pelicula> findByTitle(@Param("nombre") String nombre);

    @Query("SELECT p from Pelicula p where genero.id_genero = :estilo")
    List<Pelicula> findByGenero(@Param("estilo") Integer estilo);

    @Query("SELECT p from Pelicula p order by :order")
    List<Pelicula> orderBy(@Param("order") String order);
}
