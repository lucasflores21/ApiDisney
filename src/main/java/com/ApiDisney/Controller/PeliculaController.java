
package com.ApiDisney.Controller;

import com.ApiDisney.Entity.Pelicula;
import com.ApiDisney.Service.PeliculaService;
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
@RequestMapping("/movies")
public class PeliculaController {
    
     @Autowired
    private PeliculaService peliculaService;
    
    @GetMapping()
    public List<Pelicula> listarFilms(@RequestParam(required = false) String name,@RequestParam(required = false) Integer genre,
                                  @RequestParam(required = false) String order){
        
        if(name != null){
            return peliculaService.findByTitle(name);
        } 
        if(genre !=null){
            return peliculaService.findByGenero(genre);
        }
        if(order != null){
            return peliculaService.order(order);
        }else{
            return peliculaService.listAll();
        }
    }
    
    @PostMapping()
    public Pelicula guardarFilm(@RequestBody Pelicula peli,
                            @RequestParam(required = false) MultipartFile image) throws Exception{
        return peliculaService.crearPelicula(peli,image);
    }
    
    @DeleteMapping("/delete")
    public Boolean eliminarPersonaje(@RequestParam Integer film_id){
        return peliculaService.eliminarFilm(film_id);
    }
    
}
