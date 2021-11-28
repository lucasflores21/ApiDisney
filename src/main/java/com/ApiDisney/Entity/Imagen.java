
package com.ApiDisney.Entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "imagen")
public class Imagen {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    
    private String id;
    private String nombre;
    private String mime;
    
    @Lob 
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;
}
