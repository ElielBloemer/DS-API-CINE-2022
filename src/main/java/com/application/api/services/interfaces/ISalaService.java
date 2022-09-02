package com.application.api.services.interfaces;

import com.application.api.model.evento.Sala;
import com.application.api.vo.SalaVO;
import com.application.api.vo.SalaVO2;

public interface ISalaService {

    public Sala saveSala(SalaVO salaVO);

    public Sala saveRoomCleaningReturns(Sala sala);

    public Sala saveSala(String identificacionSala,Integer asientosDisponibles,Integer asientosOcupados,Integer idEvento);
    public Sala getSalaByIdentificacion(String identificacionSala);

    public Sala uptadeSalaByIdenficacionforSale(String identifacionSala);

    public boolean isThereChairAvailable(String identificacionSala);

    public Sala saveSalaWithFilm(String identificacionSala,String nombrePelicula);

    public Sala saveSalaWithMatch(String identificacionSala,Integer idEvento);
}
