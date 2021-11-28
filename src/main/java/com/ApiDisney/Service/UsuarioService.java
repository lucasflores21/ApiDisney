
package com.ApiDisney.Service;

import com.ApiDisney.Entity.Usuario;
import com.ApiDisney.Enums.Role;
import com.ApiDisney.Repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;








@Service("userDetailsService")
public class UsuarioService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MailService mailService;
    
    @Transactional
    public Usuario crearUsuario(Usuario user) throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        
        ArrayList<Role> rol = new ArrayList<>();
        rol.add(Role.USER);
        user.setRol(rol);
        
        //validaciones de username y password
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new Exception("El campo Username debe contener caracteres");
        }
        if (findByUsername(user.getUsername()) != null) {
            throw new Exception("El username no esta disponible.");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new Exception("El campo contraseña debe conetener caracteres");
        }
        
        
        String destinatario = user.getEmail();
        String asunto = "ApiDisney te da la bienvenida";
        String contenido = "Username: " + user.getUsername() + "\nPassword: " + user.getPassword() + "\nMail de registro: " + user.getEmail();
        
        
        mailService.enviarMail(destinatario, asunto, contenido);
        
        return usuarioRepository.save(user);
    }
    
 public Usuario findByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
    
  
    
    public Usuario findByEmail(String mail){
        return usuarioRepository.findByEmail(mail);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Usuario u = usuarioRepository.findByUsername(username);
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            ServletRequestAttributes ratt = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession sesion = ratt.getRequest().getSession(true);
            sesion.setAttribute("sesion_usuario", u); 
            return new User(username, u.getPassword(), permisos);
        }catch (Exception e){
            throw new UsernameNotFoundException("Username no existente.");
        }
    }
}
