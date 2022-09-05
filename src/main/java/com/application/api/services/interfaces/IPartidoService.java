package com.application.api.services.interfaces;

import com.application.api.model.evento.Partido;
import com.application.api.model.evento.Sala;
import com.application.api.vo.PartidoVO;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;
import java.util.Date;

public interface IPartidoService {

    public Partido getPartidoById(Integer idPartido);

    public Partido setPartidoWithSelecciones(String seleccionA, String SeleccionB, Integer calificacion, Float precio,LocalDateTime fechaEvento);

    public Partido setPartidoWithSelecciones(PartidoVO partidoVO);

    public boolean interestingCriteria(Integer idPartido);

    public boolean estaInteresante(Integer idPartido);

    public void updateMatchwithSala(Partido partido,String idPartido);

}
