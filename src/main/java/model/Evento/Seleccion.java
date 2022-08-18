package model.Evento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seleccion")
public class Seleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seleccion")
    private int id;

    @Column
    private String nombrePais;
    @Column
    private String continente;
    @Column
    private Integer mundialesGanado;
    @OneToMany
    @JoinColumn(name = "id_jugador")
    private List<Jugador> jugadoresTitulares;

    public String getNombrePais() {
        return nombrePais;
    }

    public Seleccion(String nombrePais, String continente, Integer mundialesGanado, List<Jugador> jugadoresTitulares) {
        this.nombrePais = nombrePais;
        this.continente = continente;
        this.mundialesGanado = mundialesGanado;
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public List<Jugador> getJugadoresTitulares() {
        return jugadoresTitulares;
    }

    public void setJugadoresTitulares(List<Jugador> jugadoresTitulares) {
        this.jugadoresTitulares = jugadoresTitulares;
    }

    public Integer getMundialesGanado() {
        return mundialesGanado;
    }

    public void setMundialesGanado(Integer mundialesGanado) {
        this.mundialesGanado = mundialesGanado;
    }

    public boolean contieneJugadorEstrella(){
        for(int x=0; x<jugadoresTitulares.size();x++){
            Jugador jugador = jugadoresTitulares.get(x);
            if(jugador.getNombre().contains("Lionel Messi")){
                return true;
            }
        }
        return false;
    }

    public boolean esMuyCampeon(){
        return this.getMundialesGanado() >= 5;

    }

    public Seleccion(int id, String nombrePais, String continente, Integer mundialesGanado, List<Jugador> jugadoresTitulares) {
        this.id = id;
        this.nombrePais = nombrePais;
        this.continente = continente;
        this.mundialesGanado = mundialesGanado;
        this.jugadoresTitulares = jugadoresTitulares;
    }
}
