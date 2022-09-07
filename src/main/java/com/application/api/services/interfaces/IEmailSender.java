package com.application.api.services.interfaces;

import com.application.api.vo.ReservaVOPartidoVO;
import com.application.api.vo.ReservaVOwithPeliculaVO;

import javax.mail.MessagingException;

public interface IEmailSender {
    void sendEmailWithPelicula(String email, ReservaVOwithPeliculaVO reservaVOwithPeliculaVO) throws MessagingException;

    void sendEmailWithPartido(String email, ReservaVOPartidoVO reservaVOPartidoVO) throws MessagingException;
}
