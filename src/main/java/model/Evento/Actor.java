package model.Evento;

import javax.persistence.*;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Pelicula pelicula;

    public Actor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
