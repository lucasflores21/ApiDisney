package com.ApiDisney.Service;

import com.ApiDisney.Entity.Genero;
import com.ApiDisney.Entity.Imagen;
import com.ApiDisney.Repository.GeneroRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ImagenService imagenService;

  
@Transactional
    public Genero crearGenero(Genero estilo, MultipartFile image){
        Imagen i = imagenService.save(image);
        estilo.setImagen(i);
        return generoRepository.save(estilo);
    }
    
    @Transactional
    public void modificarGenero(Genero estilo, MultipartFile image){
        Genero genero = generoRepository.findById(estilo.getId_genero()).get();
        if(genero != null){
            genero = estilo;
            crearGenero(genero,image);
        }         
    }
    
    public List<Genero> listAll(){
        return (List<Genero>) generoRepository.findAll();
    }
    
    public Optional<Genero> findById(Integer id){
        return generoRepository.findById(id);
    }
    
    public List<Genero> findByName(String name){
        return generoRepository.findByName("%" + name + "%");
    }    
    
    @Transactional
    public Boolean eliminarGenero(Genero g){
        try{
            generoRepository.delete(g);
            return true;
        } catch(Exception e){
            return false;
        }
        
    }
 public Boolean eliminarGenero(Integer gender_id) {
        Genero g = generoRepository.findById(gender_id).get();
        try{
            imagenService.delete(g.getImagen());
            g.setImagen(null);
            generoRepository.delete(g);
            return true;
        } catch(Exception e){
            return false;
        }   
        
    }
    

    
}
