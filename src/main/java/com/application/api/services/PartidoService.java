package com.application.api.services;

import com.application.api.model.evento.Jugador;
import com.application.api.model.evento.Partido;
import com.application.api.model.evento.Sala;
import com.application.api.model.evento.Seleccion;
import com.application.api.persistance.PartidoRepository;
import com.application.api.persistance.SalaRepository;
import com.application.api.persistance.SeleccionRepository;
import com.application.api.services.interfaces.IPartidoService;
import com.application.api.services.interfaces.ISalaService;
import com.application.api.services.interfaces.ISeleccionService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.PartidoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoService implements IPartidoService {

    private PartidoRepository partidoRepository;
    private SeleccionRepository seleccionRepository;
    private SalaRepository salaRepository;
    private ISeleccionService iSeleccionService;

    private final Validacion validacion = new Validacion();


    @Autowired
    public void setSeleccionRepository(SeleccionRepository seleccionRepository, ISeleccionService iSeleccionService){
        this.seleccionRepository=seleccionRepository;
        this.iSeleccionService = iSeleccionService;
    }
    @Autowired
    public void setSalaRepository(SalaRepository salaRepository){
        this.salaRepository=salaRepository;
    }

    @Autowired
    public void setPartidoRepository(PartidoRepository partidoRepository){
        this.partidoRepository=partidoRepository;
    }
    
    @Override
    public Partido getPartidoById(Integer idPartido) {
        Partido partido=partidoRepository.findPartidoById(idPartido);
        validacion.getValidacion(partido," "," THE MATCH ISNT IN THE SYSTEM");
        return partido;
    }

    @Override
    public Partido setPartidoWithSelecciones(PartidoVO partidoVO) {
        return setPartidoWithSelecciones(partidoVO.seleccionA,partidoVO.seleccionB,partidoVO.calificacionEvento,partidoVO.precioEvento,partidoVO.identificacionSala);
    }

    @Override
    public Partido setPartidoWithSelecciones(String nombreSeleccionA, String nombreSeleccionB, Integer calificacion, Float precio, String identificacionSala) {
        Seleccion seleccionA = seleccionRepository.findByNombrePais(nombreSeleccionA.toUpperCase());
        validacion.getValidacion(seleccionA,nombreSeleccionA.toUpperCase()," NO se encuentra en nuestro sistema de selecciones");
        Seleccion seleccionB = seleccionRepository.findByNombrePais(nombreSeleccionB.toUpperCase());
        validacion.getValidacion(seleccionB,nombreSeleccionB.toUpperCase()," NO se encuentra en nuestro sistema de selecciones");
        Sala sala=salaRepository.findByIdentificacionSala(identificacionSala);
        //validacion.getValidacion(seleccionB,nombreSeleccionB.toUpperCase()," NO se encuentra en nuestro sistema de sala");
        return partidoRepository.save(new Partido(seleccionA,seleccionB,calificacion,precio,sala));
    }

    @Override
    public boolean interestingCriteria(Integer idPartido) {
        Partido partido=partidoRepository.findPartidoById(idPartido);
        validacion.getValidacion(partido,idPartido.toString()," DOESNT IN THE OUR FOOTBAL MATCHS SYSTEM");
        Seleccion seleccionA=partido.getSeleccionA();
        Seleccion seleccionB=partido.getSeleccionB();
        boolean esInteresanteA=iSeleccionService.esMuyCampeona(seleccionA.getNombrePais()) || iSeleccionService.contieneJugadorEstrella(seleccionA.getNombrePais());
        boolean esInteresanteB=iSeleccionService.esMuyCampeona(seleccionB.getNombrePais()) || iSeleccionService.contieneJugadorEstrella(seleccionB.getNombrePais());
        return esInteresanteA || esInteresanteB;
    }

}