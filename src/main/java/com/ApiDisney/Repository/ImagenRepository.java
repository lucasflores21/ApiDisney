
package com.ApiDisney.Repository;

import com.ApiDisney.Entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, String> {
    
}
