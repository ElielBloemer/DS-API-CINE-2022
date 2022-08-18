package model.Evento;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "partido")
public class Partido extends Evento {

    @ManyToMany
    @JoinTable(name="equipo_partido")
    private List<Seleccion> equipos;

    public Partido(Integer id_evento, Partido partido, int calificacion, Sala salaAsignada, String fecha, double precio, TipoEvento tipoDeEvento, List<Seleccion> equipos) {
        super(id_evento, partido, calificacion, salaAsignada, fecha, precio, tipoDeEvento);
        this.equipos = equipos;
    }

    @Override
    public boolean criterioEsInteresante() {

        for (Seleccion seleccion: equipos) {
            if(seleccion.contieneJugadorEstrella() || seleccion.esMuyCampeon()){
                return true;
            }
        }
        return false;
    }
}
