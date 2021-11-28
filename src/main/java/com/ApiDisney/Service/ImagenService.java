package com.ApiDisney.Service;

import com.ApiDisney.Entity.Imagen;
import com.ApiDisney.Repository.ImagenRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImagenService {
    
    @Autowired
    private ImagenRepository imagenRepository;
    
   @Transactional
    public Imagen save(MultipartFile file) {
        if (file != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(file.getContentType());
                imagen.setNombre(file.getName());
                imagen.setContenido(file.getBytes());

                return imagenRepository.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Imagen actualizar(String idImagen, MultipartFile file) {
        if (file != null) {
            try {
                Imagen imagen = new Imagen();
                if (idImagen != null) {
                    Optional<Imagen> respuesta = imagenRepository.findById(idImagen);
                    if (respuesta.isPresent()) {
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(file.getContentType());
                imagen.setNombre(file.getName());
                imagen.setContenido(file.getBytes());

                return imagenRepository.save(imagen);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public void delete(Imagen imagen) {
        imagenRepository.delete(imagen);
    }
}
