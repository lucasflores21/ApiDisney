
package com.ApiDisney.Controller;

import com.ApiDisney.Entity.Personaje;
import com.ApiDisney.Service.PersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/characters")
public class PersonajeController {
    
      @Autowired
    private PersonajeService personajeService;
    
    @GetMapping()
    public List<Personaje> listarPersonajes(@RequestParam(required = false) String name,@RequestParam(required = false) Integer age, 
                                            @RequestParam(required = false) Integer movies ){
        if(name != null){
            return personajeService.findByName(name);
        }else if(age !=null){
            return personajeService.findByAge(age);
        }else if(movies != null){
            return personajeService.findByFilm(movies);
        }else{
            return personajeService.listAll();
        }
        
    }
    
    @PostMapping()
    public Personaje guardarPersonaje(@RequestBody Personaje character,
                                      @RequestParam(required = false) MultipartFile image) throws Exception{
        return personajeService.crearPersonaje(character, image);
    }
       
    @DeleteMapping("/delete")
    public void eliminarPersonaje(@RequestParam(required = true) Integer idpersonaje) throws Exception{
         personajeService.eliminarPersonaje(idpersonaje);
    }
    
}
