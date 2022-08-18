package model.Evento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private int idReserva;

    @OneToMany(mappedBy = "reserva")
    private List<Entrada> entradas;

    @Column(name = "numero_operacion")
    private int numeroOperacion;

    @Column(name = "esta_paga")
    private boolean estaPaga;

    @Column(name = "costo_total")
    private double costoTotal;

    public float calcularCostoTotal(){
        for (Entrada entrada : entradas) {
            this.costoTotal = Double.sum(this.getCostoTotal(), entrada.costo());
        }
        return 0;
    }

    public int getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(int numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public boolean isEstaPaga() {
        return estaPaga;
    }

    public void setEstaPaga(boolean estaPaga) {
        this.estaPaga = estaPaga;
    }

    public List<Entrada> getEntrada() {
        return entradas;
    }

    public void setEntrada(List<Entrada> entrada) {
        this.entradas = entrada;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }
}
