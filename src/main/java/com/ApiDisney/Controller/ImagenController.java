/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ApiDisney.Controller;

import com.ApiDisney.Entity.Genero;
import com.ApiDisney.Entity.Pelicula;
import com.ApiDisney.Entity.Personaje;
import com.ApiDisney.Service.GeneroService;
import com.ApiDisney.Service.PeliculaService;
import com.ApiDisney.Service.PersonajeService;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/img")
public class ImagenController {

    @Autowired
    private PersonajeService personajeService;
    
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private GeneroService generoService;
            
            
            
            
    @GetMapping("/character")
    public ResponseEntity<byte[]> imagenPersonaje(@RequestParam(required = true) Integer id) {

        try {
            Personaje p = personajeService.findById(id).get();
            if (p.getImagen() == null) {
                throw new Exception("El personaje no tiene una imagen asignada.");
            }
            byte[] imagen = p.getImagen().getContenido();
            HttpHeaders hh = new HttpHeaders();
            hh.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imagen, hh, HttpStatus.OK);

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ImagenController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/movies")
    public ResponseEntity<byte[]> imagenMovies(@RequestParam(required = true) Integer id) {

        try {
            Pelicula peli = peliculaService.findById(id).get();
            if (peli.getImagen() == null) {
                throw new Exception("imagen null.");
            }
            byte[] imagen = peli.getImagen().getContenido();
            HttpHeaders hh = new HttpHeaders();
            hh.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imagen, hh, HttpStatus.OK);

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ImagenController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/gender")
    public ResponseEntity<byte[]> imagenGenero(@RequestParam(required = true) Integer id) {

        try {
            Genero g = generoService.findById(id).get();
            if (g.getImagen() == null) {
                throw new Exception("imagen null.");
            }
            byte[] imagen = g.getImagen().getContenido();
            HttpHeaders hh = new HttpHeaders();
            hh.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imagen, hh, HttpStatus.OK);

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ImagenController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
