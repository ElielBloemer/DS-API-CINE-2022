package model.Evento;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pelicula")
public class Pelicula extends Evento {

    @Column
    private String productora;

    @OneToMany
    private List<Actor> elenco;

    @Column
    private int minutosPelicula;

    public int getMinutosPelicula() {
        return minutosPelicula;
    }

    public void setMinutosPelicula(int minutosPelicula) {
        this.minutosPelicula = minutosPelicula;
    }

    public List<Actor> getElenco() {
        return elenco;
    }

    public void setElenco(List<Actor> elenco) {
        this.elenco = elenco;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    @Override
    public boolean criterioEsInteresante() {
        return this.productoraEsFamosa() || (this.tieneActoreEstrella() && this.tieneMuchosMinutos()) ;
    }

    public boolean productoraEsFamosa(){
        return this.getProductora().equals("Marvel");
    }

    public boolean tieneMuchosMinutos(){
        return this.getMinutosPelicula() >=200 && this.getMinutosPelicula()<=240;
    }

    public boolean tieneActoreEstrella(){
        for (Actor actor : elenco) {
            if (actor.getNombre().contains("Dwayne Johnson") || actor.getNombre().contains("Vin Diesel")) {
                return true;
            }
        }
        return false;
    }

    public Pelicula(Integer id_evento, Pelicula pelicula, int calificacion, Sala salaAsignada, String fecha, double precio, TipoEvento tipoDeEvento, String productora, List<Actor> elenco, int minutosPelicula) {
        super(id_evento, pelicula, calificacion, salaAsignada, fecha, precio, tipoDeEvento);
        this.productora = productora;
        this.elenco = elenco;
        this.minutosPelicula = minutosPelicula;
    }
}
