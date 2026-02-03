package es.uma.taw.sceneit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "permisos")
public class Permisos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPermisos", nullable = false)
    private Integer id;

    @Column(name = "leerAdmin")
    private Byte leerAdmin;

    @Column(name = "escribirAdmin")
    private Byte escribirAdmin;

    @Column(name = "leerEditor")
    private Byte leerEditor;

    @Column(name = "escribirEditor")
    private Byte escribirEditor;

    @Column(name = "leerEstadista")
    private Byte leerEstadista;

    @Column(name = "escribirEstadista")
    private Byte escribirEstadista;

    @Column(name = "leerUsuario")
    private Byte leerUsuario;

    @Column(name = "escribirUsuario")
    private Byte escribirUsuario;




}
