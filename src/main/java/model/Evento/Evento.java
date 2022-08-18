package model.Evento;

import javax.persistence.*;

@Entity
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Evento{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_evento")
    private Integer id_evento;

    @Transient
    private Pelicula pelicula;

    @Transient
    private Partido partido;

    @Column
    private int calificacion;

    @OneToOne
    @JoinColumn(name = "id_sala")
    private Sala salaAsignada;
    @Column
    private String fecha;
    @Column
    private double precio;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipoDeEvento;

    public Evento(Integer id_evento, Pelicula pelicula, int calificacion, Sala salaAsignada, String fecha, double precio, TipoEvento tipoDeEvento) {
        this.id_evento = id_evento;
        this.pelicula = pelicula;
        this.calificacion = calificacion;
        this.salaAsignada = salaAsignada;
        this.fecha = fecha;
        this.precio = precio;
        this.tipoDeEvento = tipoDeEvento;
    }

    public Evento(Integer id_evento, Partido partido, int calificacion, Sala salaAsignada, String fecha, double precio, TipoEvento tipoDeEvento) {
        this.id_evento = id_evento;
        this.partido = partido;
        this.calificacion = calificacion;
        this.salaAsignada = salaAsignada;
        this.fecha = fecha;
        this.precio = precio;
        this.tipoDeEvento = tipoDeEvento;
    }

    public Sala getSalaAsignada() {
        return salaAsignada;
    }

    public Evento() {
    }

    public void setSalaAsignada(Sala salaAsignada) {
        this.salaAsignada = salaAsignada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Evento(int calificacion, double precio) {
        this.calificacion = calificacion;
        this.precio = precio;
    }

    public Evento(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean esInteresante(){
        return this.getCalificacion() >=8 && criterioEsInteresante();
    }

    public abstract boolean criterioEsInteresante();

}
