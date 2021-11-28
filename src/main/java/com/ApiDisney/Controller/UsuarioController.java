
package com.ApiDisney.Controller;

import com.ApiDisney.Entity.Usuario;
import com.ApiDisney.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    
     @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/register")
    public Usuario registro(@RequestBody Usuario usuario) throws Exception{
        return usuarioService.crearUsuario(usuario);
    }
    
    @GetMapping("/login")
    public String iniciarSesion(Model model,@RequestParam(required = false) String error,@RequestParam(required = false) String username,
                                @RequestParam(required = false) String logout){
        
        if(error != null){
            model.addAttribute("error", "Usuario o contraseña invalido");
            return "error";
        }
        if(username != null){
            model.addAttribute("username",username);
        }
        return "redirect:/";
    }
    
    
}
