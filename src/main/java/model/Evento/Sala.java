package model.Evento;

import javax.persistence.*;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private int idSala;

    @Column
    private Integer espacioTotal;

    @Column
    private Integer espacioDisponible=100;

    @OneToOne
    @JoinColumn(name = "id_evento")
    private Evento eventoAsignado;

    public int getidSala() {
        return idSala;
    }

    public void setidSala(int id) {
        this.idSala = idSala;
    }

    public Integer getEspacioTotal() {
        return espacioTotal;
    }

    public void setEspacioTotal(Integer espacioTotal) {
        this.espacioTotal = espacioTotal;
    }

    public Integer getEspacioDisponible() {
        return espacioDisponible;
    }

    public void setEspacioDisponible(Integer espacioDisponible) {
        this.espacioDisponible = espacioDisponible;
    }

    public Evento getEventoAsignado() {
        return eventoAsignado;
    }

    public void setEventoAsignado(Evento eventoAsignado) {
        this.eventoAsignado = eventoAsignado;
    }

    public boolean hayEspaciosDisponible() {
        return this.getEspacioDisponible() > 0;
    }

    public void restarEspacioDisponible() {
        this.espacioDisponible--;
    }
}
