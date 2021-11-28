
package com.ApiDisney.Service;

import com.ApiDisney.Entity.Imagen;
import com.ApiDisney.Entity.Pelicula;
import com.ApiDisney.Repository.PeliculaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PeliculaService {
    
    @Autowired
    private PeliculaRepository peliculaRepository;
    
    @Autowired
    private ImagenService imagenService;
    
    @Transactional
    public Pelicula crearPelicula(Pelicula peli, MultipartFile imagen) throws Exception{
        Pelicula pelicula = new Pelicula();
        if(validarPuntos(peli.getCalificacion())){
            pelicula.setCalificacion(peli.getCalificacion());
        } else{
            throw new Exception("Puntuacion no corresponde.");
        }
        if(peli.getTitulo() == null){
            throw new Exception("Ingrese un titulo válido");
        }      
        
        pelicula.setTitulo(peli.getTitulo());
        pelicula.setFecha_creacion(peli.getFecha_creacion());
        pelicula.setGenero(peli.getGenero());        
        
        Imagen i = imagenService.save(imagen);
        peli.setImagen(i);
        return peliculaRepository.save(pelicula);
    }
    
    
    @Transactional
    public void modificarFilm(Pelicula peli, MultipartFile imagen) throws Exception{
        Pelicula pelicula = peliculaRepository.findById(peli.getId_pelicula()).get();
        if(pelicula != null){
            pelicula = peli;
            crearPelicula(pelicula,imagen);
        }         
    }
    
    public List<Pelicula> listAll(){
        return (List<Pelicula>)peliculaRepository.findAll();
    }
    
    public Optional<Pelicula> findById(Integer id){
        return peliculaRepository.findById(id);
    }
    
    public List<Pelicula> findByTitle(String title){
        return peliculaRepository.findByTitle("%" + title + "%");
    }
    
    @Transactional
    public Boolean eliminarFilm(Integer id_pelicula){
        Pelicula pelicula = peliculaRepository.findById(id_pelicula).get();
        try{
            imagenService.delete(pelicula.getImagen());
            pelicula.setImagen(null);
            peliculaRepository.delete(pelicula);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public List<Pelicula> findByGenero(Integer genre) {
        return peliculaRepository.findByGenero(genre);
    }

    public List<Pelicula> order(String order) {
        try{
            order = order.toUpperCase();
            return peliculaRepository.orderBy(order);
        }catch (Exception e){
            return listAll();
        }
    }
    
    public Boolean validarPuntos(Integer puntos){
        if(puntos <= 5 && puntos >0){
            return true;
        } else{
            return false;
        }
    }
    
}