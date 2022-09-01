package com.application.api.services;

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

    private SeleccionRepository seleccionRepository;

    @Autowired
    public void setJugadorRepository(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Autowired
    public void setSeleccionRepository(SeleccionRepository seleccionRepository){
        this.seleccionRepository=seleccionRepository;
    }

    //@Override
    //public Jugador getJugadorById(Integer id) {
        //return jugadorRepository.findById(id).orElseThrow(()-> new NotFoundException(" El jugador com el id: "+id+"no existe en nuestro registros"));
      //  return null;
    //}

    @Override
    public Jugador getJugadorByNombre(String nombreJugador) {
        Jugador jugador=jugadorRepository.findByNombreJugador(nombreJugador);
        objectoExiste(jugador,nombreJugador);
        return jugador;
    }

    @Override
    public Jugador guardarJugador(JugadorVO jugadorVO) {
        return guardarJugador(jugadorVO.nombre.toUpperCase(), jugadorVO.esEstrella, jugadorVO.nombreSeleccion);
    }

    @Override
    public Jugador guardarJugador(String nombre,boolean esEstrella,String nombreSeleccion) {
        // String nombreMayusculo = nombre.toUpperCase();
         //String nombreSeleccionMayusculo = nombreSeleccion.toUpperCase();
         Jugador jugador=jugadorRepository.findByNombreJugador(nombre.toUpperCase());
         Seleccion seleccion=seleccionRepository.findByNombrePais(nombreSeleccion.toUpperCase());
         objectoExiste(seleccion,nombreSeleccion);
         estaEnElSistema(jugador,nombre.toUpperCase());
        return jugadorRepository.save(new Jugador(nombre.toUpperCase(),esEstrella,seleccion));
    }

   /* private void esaSeleccionEstaEnElSistema(Seleccion seleccion, String nombreSeleccionMayusculo) {
        if(seleccion==null)
            throw new NotFoundException("La Seleccion com nombre "+nombreSeleccionMayusculo+" no existe en el sistema");
    }*/

    private void estaEnElSistema(Object object, String nombreDato) {
        if(object!=null)
            throw new NotFoundException(nombreDato+ " YA Existe en nuestro sistema".toUpperCase());
    }

    private void objectoExiste(Object object, String nombreDato) {
        if(object==null)
            throw new NotFoundException(nombreDato.toUpperCase()+ " NO existe en nuestro sistema,Porfavor REGISTRELO PRIMERO");
    }
}
