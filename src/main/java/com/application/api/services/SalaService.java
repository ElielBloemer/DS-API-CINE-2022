package com.application.api.services;

import com.application.api.model.evento.Sala;
import com.application.api.persistance.SalaRepository;
import com.application.api.services.interfaces.ISalaService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.SalaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService implements ISalaService {

    private SalaRepository salaRepository;

    private final Validacion validacion = new Validacion();

    @Autowired
    public void SalaRepository(SalaRepository salaRepository){
        this.salaRepository=salaRepository;
    }

    @Override
    public Sala saveSala(SalaVO salaVO) {
        return saveSala(salaVO.identificacionSala, salaVO.asientosDisponibles,salaVO.asientoReservados);
    }

    @Override
    public Sala saveSala(String identificacionSala,Integer asientosDisponibles,Integer asientosOcupados) {
        Sala sala=salaRepository.findByIdentificacionSala(identificacionSala.toUpperCase());
        validacion.getValidacionSiEstaEnElSistema(sala,identificacionSala.toUpperCase()," esa SALA YA existe en nuestro sistema, porfavor Verifique el ID de sala");
        return salaRepository.save(new Sala(identificacionSala.toUpperCase(), asientosDisponibles,asientosOcupados,false));
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

    public void updateChairInCinema(Sala sala){
        if(sala.getAsientosDisponibles()==0)
            throw new NullPointerException("OPS SORRY. ROOM FULL!");
        sala.setAsientosDisponibles(sala.getAsientosDisponibles()-1);
        sala.setAsientoReservados(sala.getAsientoReservados()+1);
    }
}
