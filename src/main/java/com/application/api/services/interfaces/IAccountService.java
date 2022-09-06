package com.application.api.services.interfaces;

import com.application.api.vo.ReservaVOPartidoVO;
import com.application.api.vo.ReservaVOwithPeliculaVO;

import javax.mail.MessagingException;

public interface IAccountService {
    public void sendEmailWithPelicula(String email, ReservaVOwithPeliculaVO reservaVOwithPeliculaVO) throws MessagingException;

   public void sendEmailWithPartido(String email, ReservaVOPartidoVO reservaVOPartidoVO) throws MessagingException;
}
