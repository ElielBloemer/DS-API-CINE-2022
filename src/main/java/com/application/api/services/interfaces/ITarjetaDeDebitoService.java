package com.application.api.services.interfaces;

import com.application.api.model.tarjetadedebito.TarjetaDeDebito;
import com.application.api.vo.TarjetaDeDebitoVO;

public interface ITarjetaDeDebitoService {

    public boolean pagar(float valorAPagar,String monedaElegida,String numeroTarjeta);
    public TarjetaDeDebito getTarjetaDeDebitoByNumber(String numeroTarjeta);
    public TarjetaDeDebito saveTarjetaDeDebito(TarjetaDeDebitoVO tarjetaDeDebitoVO);

    public boolean pagarConMonedaExtranjera(TarjetaDeDebito tarjetaDeDebito,float deudaAPagar);

    public boolean pagarConMonedaLocal(TarjetaDeDebito tarjetaDeDebito,float deudaAPagar);

    public TarjetaDeDebito saveTarjetaDeDebito(String numeroTarjeta,String titular,float dineroDisponibleMonedaLocal, float dineroDisponibleMonedaExtranjera, String monedaExtranjera);
}
