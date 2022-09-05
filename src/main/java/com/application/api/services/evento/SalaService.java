package com.application.api.services.evento;

import com.application.api.model.evento.Evento;
import com.application.api.model.evento.Partido;
import com.application.api.model.evento.Pelicula;
import com.application.api.model.evento.Sala;
import com.application.api.persistance.PeliculaRepository;
import com.application.api.persistance.SalaRepository;
import com.application.api.services.interfaces.IPartidoService;
import com.application.api.services.interfaces.IPeliculaService;
import com.application.api.services.interfaces.ISalaService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.SalaVO;
import com.application.api.vo.SalaVO2;
import org.hibernate.boot.model.naming.ImplicitTenantIdColumnNameSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class SalaService implements ISalaService {

    private SalaRepository salaRepository;

    private IPartidoService iPartidoService;
    private IPeliculaService iPeliculaService;
    private final Validacion validacion = new Validacion();

    @Autowired
    public void SalaRepository(SalaRepository salaRepository,IPartidoService iPartidoService,IPeliculaService iPeliculaService){
        this.salaRepository=salaRepository;
        this.iPeliculaService=iPeliculaService;
        this.iPartidoService=iPartidoService;
    }

    @Override
    public Sala saveSala(SalaVO salaVO) {
        return saveSala(salaVO.identificacionSala, salaVO.asientosDisponibles,0,0);
    }

    @Override
    public Sala saveRoomCleaningReturns(Sala sala) {
        return salaRepository.save(sala);
    }

    @Override
    public Sala saveSala(String identificacionSala, Integer asientosDisponibles, Integer asientosOcupados, Integer idEvento) {
        Sala sala=salaRepository.findByIdentificacionSala(identificacionSala.toUpperCase());
        validacion.getValidacionSiEstaEnElSistema(sala,identificacionSala.toUpperCase()," esa SALA YA existe en nuestro sistema, porfavor Verifique el ID de sala");
       // Pelicula pelicula=iPeliculaService.getPeliculaById(idEvento);
        //Partido partido=iPartidoService.getPartidoById(idEvento);
        return salaRepository.save(new Sala(identificacionSala.toUpperCase(), asientosDisponibles,asientosOcupados,false,null));
    }

    @Override
    public Sala getSalaByIdentificacion(String identificacionSala) {
        Sala sala=salaRepository.findByIdentificacionSala(identificacionSala);
        validacion.getValidacion(sala," "," NO existe en nuestro sistema de SALA, registre dicha SALA \n");
        return sala;
    }

    @Override
    public Sala uptadeSalaByIdenficacionforSale(String identifacionSala) {
        Sala sala=getSalaByIdentificacion(identifacionSala);
        updateChairInCinema(sala);
        return salaRepository.save(sala);
    }

    @Override
    public boolean isThereChairAvailable(String identificacionSala) {
        Sala sala=getSalaByIdentificacion(identificacionSala);
        return sala.getAsientosDisponibles()>0;
    }

    @Override
    public Sala saveSalaWithFilm(String identificacionSala,String nombrePelicula) {
        Sala sala=getSalaByIdentificacion(identificacionSala.toUpperCase());
        if(sala.isTieneEventoAsignado()){
          validacion.getValidacionSiEstaEnElSistema(sala," "," Esta SALA YA tiene un evento asignado,porfavor registre otra sala");}
        Pelicula pelicula=iPeliculaService.getPeliculaByName(nombrePelicula);
        validacion.getValidacion(pelicula,nombrePelicula," NO esta en nuestro sistema de PELICULA,porfavor registrala");
        sala.setEvento(pelicula);
        sala.setTieneEventoAsignado(true);
        return salaRepository.save(sala);
    }

    @Override
    public Sala saveSalaWithMatch(String identificacionSala, Integer idEvento) {
        Sala sala=getSalaByIdentificacion(identificacionSala.toUpperCase());
        if(sala.isTieneEventoAsignado()){
            validacion.getValidacionSiEstaEnElSistema(sala," "," Esta SALA YA tiene un evento asignado,porfavor registre otra sala");}
        Partido partido=iPartidoService.getPartidoById(idEvento);
        validacion.getValidacion(partido,"PARTIDO"," NO existente en nuestro sistema de PARTIDOS,PORFAVOR REGISTRALO.");
        sala.setEvento(partido);
        sala.setTieneEventoAsignado(true);
        return salaRepository.save(sala);
    }

    public void updateChairInCinema(Sala sala){
        if(sala.getAsientosDisponibles()==0)
            throw new NullPointerException("OPS SORRY. ROOM FULL!");
        sala.setAsientosDisponibles(sala.getAsientosDisponibles()-1);
        sala.setAsientoReservados(sala.getAsientoReservados()+1);
    }
}
