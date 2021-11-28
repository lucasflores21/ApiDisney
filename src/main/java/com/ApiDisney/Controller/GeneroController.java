
package com.ApiDisney.Controller;

import com.ApiDisney.Entity.Genero;
import com.ApiDisney.Service.GeneroService;
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
@RequestMapping("/genre")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping()
    public List<Genero> listarGeneros() {
        return generoService.listAll();
    }

    @PostMapping
    public Genero nuevoGenero(@RequestBody Genero gender,
            @RequestParam(required = false) MultipartFile image) {
        return generoService.crearGenero(gender, image);
    }

    @DeleteMapping("/delete")
    public Boolean eliminarGenero(@RequestParam(required = true) Integer gender_id) {
        return generoService.eliminarGenero(gender_id);
    }


}
