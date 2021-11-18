
package com.ApiDisney.Service;

import com.ApiDisney.Entity.Personaje;
import com.ApiDisney.Repository.PersonajeRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeService {
    
    //CRUD
      
    @Autowired
    private PersonajeRepository personajeRepository;
    
    @Transactional
    public Personaje crearPersonaje(Personaje personaje){

        return personajeRepository.save(personaje);
    }
    
    @Transactional
    public void modificarPersonaje(Personaje p){
        Personaje personaje = personajeRepository.findById(p.getId_personaje()).get();
        if(personaje != null){
            personaje = p;
            crearPersonaje(personaje);
        }         
    }        
    
    
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
    
    
    @Transactional
    public Boolean eliminarPersonaje(Personaje p){
        try{
            personajeRepository.delete(p);
            return true;
        } catch(Exception e){
            return false;
        }
        
    }


}
