package com.application.api.services;

import com.application.api.controllers.SeleccionController;
import com.application.api.model.evento.Jugador;
import com.application.api.model.evento.Seleccion;
import com.application.api.persistance.SeleccionRepository;
import com.application.api.services.interfaces.ISeleccionService;
import com.application.api.vo.SeleccionVO;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class SeleccionService implements ISeleccionService {

    private SeleccionRepository seleccionRepository;

    @Autowired
    public void setSeleccionService(SeleccionRepository seleccionRepository){
        this.seleccionRepository=seleccionRepository;
    }

    @Override
    public Seleccion getSeleccionPorNombre(String nombre) {
        Seleccion seleccion = seleccionRepository.findByName(nombre);
        seleccionExiste(seleccion,nombre);
        return seleccion;
    }

    @Override
    public Seleccion guardarSeleccion(String nombrePais, String continente, List<Jugador> jugadores, Integer mundialesGanados) {
        Seleccion nuevaSeleccion = seleccionRepository.findByName(nombrePais);
        estaEnElSistema(nuevaSeleccion,nombrePais);
        String nombreMayusculo= nombrePais.toUpperCase();
        String continenteMayusculo=continente.toUpperCase();
        return seleccionRepository.save(new Seleccion(nombreMayusculo,continenteMayusculo,jugadores,mundialesGanados));
    }

    @Override
    public Boolean contieneJugadorEstrella(String nombreSelecion) {
        return null;
    }

    public void seleccionExiste(Seleccion seleccion,String nombreSelecion){
        if(seleccion==null){
            throw new NotFoundException("La Seleccion com nombre "+ nombreSelecion + "no existe en el sistema");
        }
    }

    public void estaEnElSistema(Seleccion seleccion,String nombre) {
        if (seleccion != null) {
            throw new NotFoundException("la seleccion" + nombre + " ya esta en el sistema!");
        }
    }
}
