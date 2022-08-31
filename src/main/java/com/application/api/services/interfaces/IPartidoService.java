package com.application.api.services.interfaces;

import com.application.api.model.evento.Partido;
import com.application.api.vo.PartidoVO;

public interface IPartidoService {

    public Partido getPartidoById(Integer idPartido);

    public Partido setPartidoWithSelecciones(String seleccionA,String SeleccionB);

    public Partido setPartidoWithSelecciones(PartidoVO partidoVO);

    public boolean interestingCriteria(Integer idPartido);

}
