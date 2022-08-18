package model.Evento;

import model.Descuento.DescuentoMenor;
import model.Usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "entrada")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada")
    private int idEntrada;
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    private Evento evento;
    @Column
    private double precio;
    @Column
    private String fechaEvento;
    @Transient
    private double descuentoOtorgado;
    @Transient
    private DescuentoMenor descuento;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_reserva",referencedColumnName = "id_reserva")
    private Reserva reserva;

    @OneToOne
    @JoinColumn(name = "id_sala")
    private Sala sala;

    public Entrada() {
    }

    public Entrada(int idEntrada, Usuario usuario, Evento evento, double precio, String fechaEvento, double descuentoOtorgado, DescuentoMenor descuento, Sala sala) {
        this.idEntrada = idEntrada;
        this.usuario = usuario;
        this.evento = evento;
        this.precio = precio;
        this.fechaEvento = fechaEvento;
        this.descuentoOtorgado = descuentoOtorgado;
        this.descuento = descuento;
        this.sala = sala;
    }

    public DescuentoMenor getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentoMenor descuento) {
        this.descuento = descuento;
    }

    //Aca cuando vengo de consultar por mi descuento, agarro mi descuento calculado y hago el costo real de la entrada
    public double costo() {
       return this.setPrecio(this.getPrecio() - this.getDescuentoOtorgado());
    }

    public double getDescuentoOtorgado() {
        return descuentoOtorgado;
    }

    public void setDescuentoOtorgado(double descuentoOtorgado) {
        this.descuentoOtorgado = descuentoOtorgado;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public double getPrecio() {
        return precio;
    }

    public double setPrecio(double precio) {
        this.precio = precio;
        return precio;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public boolean verificarDisponibilidad(){
        return sala.hayEspaciosDisponible();
    }

    public void asignarValores(Evento eventoElegido) {
        this.precio = eventoElegido.getPrecio();
        this.fechaEvento = eventoElegido.getFecha();
    }
}
