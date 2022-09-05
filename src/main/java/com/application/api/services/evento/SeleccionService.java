package com.application.api.services.evento;

import com.application.api.model.evento.Jugador;
import com.application.api.model.evento.Seleccion;
import com.application.api.persistance.JugadorRepository;
import com.application.api.persistance.SeleccionRepository;
import com.application.api.services.interfaces.ISeleccionService;
import com.application.api.vo.SeleccionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class SeleccionService implements ISeleccionService {

    private SeleccionRepository seleccionRepository;

    /*private JugadorRepository jugadorRepository;

    @Autowired
    public void setJugadorService(JugadorRepository jugadorRepository){
        this.jugadorRepository=jugadorRepository;
    }*/

    @Autowired
    public void setSeleccionService(SeleccionRepository seleccionRepository){
        this.seleccionRepository=seleccionRepository;
    }

    @Override
    public Seleccion guardarSeleccion(SeleccionVO selecioVO) {
        return guardarSeleccion(selecioVO.nombrePais,selecioVO.continente,null,selecioVO.mundialesGanados);
    }

    @Override
    public Seleccion guardarSeleccion(String nombrePais, String continente,List<Jugador> jugadores, Integer mundialesGanados) {
        //String nombreMayusculo= nombrePais.toUpperCase();
        Seleccion nuevaSeleccion = seleccionRepository.findByNombrePais(nombrePais.toUpperCase());
        estaEnElSistema(nuevaSeleccion,nombrePais);
        // String continenteMayusculo=continente.toUpperCase();
        return seleccionRepository.save(new Seleccion(nombrePais.toUpperCase(),continente.toUpperCase(),jugadores,mundialesGanados));
    }

    @Override
    public Seleccion getSeleccionPorNombre(String nombre) {
        Seleccion seleccion = seleccionRepository.findByNombrePais(nombre.toUpperCase());
        seleccionExiste(seleccion,nombre.toUpperCase());
        //List<Jugador> jugadores = jugadorRepository.findByNombreSeleccion(nombre.toUpperCase());
        //seleccion.setJugadorTitulares(existenJugadores(jugadores));
        return seleccion;
    }

    private List<Jugador> existenJugadores(List<Jugador> jugadores) {
        return jugadores;
    }


    @Override
    public boolean contieneJugadorEstrella(String nombreSelecion) {
        Seleccion seleccion = getSeleccionPorNombre(nombreSelecion);
        List<Jugador> jugadores = seleccion.getJugadorTitulares();
        for ( Jugador jugador: jugadores) {
            if(jugador.isEsEstrella()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean esMuyCampeona(String nombreSeleccion) {
        Seleccion seleccion = getSeleccionPorNombre(nombreSeleccion);
        return seleccion.getMundialesGanados() >= 5;
    }

    @Override
    public void validarInformacion(String nombrePais) {
            if(nombrePais.equals("STRING")){
                throw new NotFoundException("Nombre INVALIDO para la seleccion");
            }
    }

    public void seleccionExiste(Seleccion seleccion,String nombreSelecion){
        if(seleccion==null){
            throw new NotFoundException("La Seleccion com nombre "+ nombreSelecion.toUpperCase() + " NO existe en el sistema");
        }
    }

    public void estaEnElSistema(Seleccion seleccion,String nombre) {
        if (seleccion != null) {
            throw new NotFoundException("la seleccion " + nombre.toUpperCase() + " ya esta en el sistema!");
        }
    }
}
