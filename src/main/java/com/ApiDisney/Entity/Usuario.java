
package com.ApiDisney.Entity;

import com.ApiDisney.Enums.Role;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer user_id;
 
    
    private String email;
    private String username;
    private String password;
    
    private ArrayList<Role> rol;
    
}
   
