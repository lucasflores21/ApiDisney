
package com.ApiDisney.Service;

import com.ApiDisney.Entity.Imagen;
import com.ApiDisney.Entity.Personaje;
import com.ApiDisney.Repository.PersonajeRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonajeService {
    
    //CRUD
      
    @Autowired
    private PersonajeRepository personajeRepository;
    
    @Autowired
    private ImagenService imagenService;
    
    
    @Transactional
    public Personaje crearPersonaje(Personaje pers, MultipartFile imagen) throws Exception{
        Personaje p = new Personaje();
        
        
        //faltan validaciones
        
        p.setNombre(pers.getNombre());
        p.setEdad(pers.getEdad());
        p.setPeso(pers.getPeso());
        p.setPelicula(pers.getPelicula());
        p.setHistoria(pers.getHistoria());
        
        Imagen i = imagenService.save(imagen);
        p.setImagen(i);
        return personajeRepository.save(p);
    }
    
    @Transactional
    public void modificarPersonaje(Personaje pers, MultipartFile imagen) throws Exception{
        Personaje personaje = personajeRepository.findById(pers.getId_personaje()).get();
        if(personaje != null){
            personaje = pers;
            crearPersonaje(personaje,imagen);
        }         
    }  
    
    @Transactional
    public void eliminarPersonaje(Integer id) throws Exception{
        try{
            personajeRepository.deleteById(id);
            
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
        
    }
    
    //Querys
    
    public List<Personaje> listAll(){
        return (List<Personaje>) personajeRepository.findAll();
    }
    
    public Optional<Personaje> findById(Integer id){
        return personajeRepository.findById(id);
    }
    
    public List<Personaje> findByName(String name){
        return personajeRepository.findByName("%" + name + "%");
    }
    
    
    public List<Personaje> findByAge(Integer age){
        return personajeRepository.findByAge(age);
    }
    
    public List<Personaje> findByFilm(Integer idMovie){
        return personajeRepository.findByPeli(idMovie);
        
    }
    


}
