package com.application.api.services;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Jugador;
import com.application.api.model.evento.Seleccion;
import com.application.api.persistance.JugadorRepository;
import com.application.api.persistance.SeleccionRepository;
import com.application.api.services.interfaces.IJugadorService;
import com.application.api.vo.JugadorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class JugadorService implements IJugadorService {

    private JugadorRepository jugadorRepository;

    //private SeleccionRepository seleccionRepository;

    @Autowired
    public void setJugadorRepository(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    /*@Autowired
    public void setSeleccionService(SeleccionRepository seleccionRepository){
        this.seleccionRepository=seleccionRepository;
    }*/

    //@Override
    //public Jugador getJugadorById(Integer id) {
        //return jugadorRepository.findById(id).orElseThrow(()-> new NotFoundException(" El jugador com el id: "+id+"no existe en nuestro registros"));
      //  return null;
    //}

    @Override
    public Jugador getJugadorByNombre(String nombreJugador) {
        Jugador jugador=jugadorRepository.findByNombreJugador(nombreJugador);
        jugadorExiste(jugador,nombreJugador);
        return jugador;
    }

    @Override
    public Jugador guardarJugador(JugadorVO jugadorVO) {
        return guardarJugador(jugadorVO.nombre.toUpperCase(), jugadorVO.esEstrella, jugadorVO.nombreSeleccion);
    }

    @Override
    public Jugador guardarJugador(String nombre,boolean esEstrella,String nombreSeleccion) {
         String nombreMayusculo = nombre.toUpperCase();
         String nombreSeleccionMayusculo = nombreSeleccion.toUpperCase();
         Jugador jugador=jugadorRepository.findByNombreJugador(nombreMayusculo);
         estaEnElSistema(jugador,nombre);
        return jugadorRepository.save(new Jugador(nombreMayusculo,esEstrella,nombreSeleccionMayusculo));
    }

    private void esaSeleccionEstaEnElSistema(Seleccion seleccion, String nombreSeleccionMayusculo) {
        if(seleccion==null){
            throw new NotFoundException("La Seleccion com nombre "+ nombreSeleccionMayusculo + " no existe en el sistema");
        }
    }

    private void estaEnElSistema(Jugador jugador, String nombreJugador) {
        if(jugador!=null)
            throw new NotFoundException("Jugador: "+nombreJugador+"ya Existe en el sistema");
    }

    private void jugadorExiste(Jugador jugador,String nombreJugador) {
        if(jugador==null)
            throw new NotFoundException("Jugador: "+ nombreJugador+" no existe en el sistema");

    }
}
