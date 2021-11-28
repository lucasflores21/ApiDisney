
package com.ApiDisney.Repository;

import com.ApiDisney.Entity.Genero;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    
    @Query("SELECT g FROM Genero g where g.nombre like :name")
    List<Genero> findByName(@Param("name") String name);
    
}
