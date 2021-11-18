
package com.ApiDisney.Controller;

import com.ApiDisney.Entity.Personaje;
import com.ApiDisney.Service.PersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/characters")
public class PersonajeController {
    
    @Autowired
    private PersonajeService personajeService;
    
    @GetMapping()
    public List<Personaje> listarPersonajes(){
        return personajeService.listAll();
    }
    
    @PostMapping()
    public Personaje guardarPersonaje(@RequestBody Personaje personaje){

        return personajeService.crearPersonaje(personaje);
    }
    
}
