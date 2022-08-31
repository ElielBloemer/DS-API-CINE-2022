package com.application.api.services.interfaces;

import com.application.api.model.evento.Sala;
import com.application.api.vo.SalaVO;

public interface ISalaService {

    public Sala saveSala(SalaVO salaVO);

    public Sala saveSala(String identificacionSala,Integer asientosDisponibles,Integer asientosOcupados);
    public Sala getSalaByIdentificacion(String identificacionSala);

    public Sala uptadeSalaByIdenficacionforSale(String identifacionSala);

    public boolean isThereChairAvailable(String identificacionSala);

}
