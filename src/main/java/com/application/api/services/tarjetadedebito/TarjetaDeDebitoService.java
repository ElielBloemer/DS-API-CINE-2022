package com.application.api.services.tarjetadedebito;

import com.application.api.model.tarjetadedebito.TarjetaDeDebito;
import com.application.api.persistance.TarjetaRepository;
import com.application.api.services.interfaces.ITarjetaDeDebitoService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.TarjetaDeDebitoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaDeDebitoService implements ITarjetaDeDebitoService {

    private TarjetaRepository tarjetaRepository;
    private final Validacion validacion = new Validacion();

    @Autowired
    public void setTarjetaRepository(TarjetaRepository tarjetaRepository){
        this.tarjetaRepository=tarjetaRepository;
    }

    @Override
    public boolean pagar(float valorAPagar, String monedaElegida,String numeroTarjeta) {
        TarjetaDeDebito tarjetaDeDebito=getTarjetaDeDebitoByNumber(numeroTarjeta);
        validacion.getValidacion(tarjetaDeDebito,numeroTarjeta.toString()," NO esta en nuestro sistema de Tarjetas");
        if(tarjetaDeDebito.getMonedaNacional().equals(monedaElegida.toUpperCase()) && tarjetaDeDebito.getDineroDisponibleMonedaLocal()>=valorAPagar){
            tarjetaDeDebito.setDineroDisponibleMonedaLocal(tarjetaDeDebito.getDineroDisponibleMonedaLocal()-valorAPagar);
            tarjetaRepository.save(tarjetaDeDebito);
            return true;
        } else if (tarjetaDeDebito.getMonedaExtranjera().equals(monedaElegida.toUpperCase()) && tarjetaDeDebito.getDineroDisponibleMonedaExtranjera() >= valorAPagar) {

            tarjetaDeDebito.setDineroDisponibleMonedaExtranjera(tarjetaDeDebito.getDineroDisponibleMonedaExtranjera()-valorAPagar);
            tarjetaRepository.save(tarjetaDeDebito);
            return true;
        }
        return false;
    }

    @Override
    public TarjetaDeDebito getTarjetaDeDebitoByNumber(String numeroTarjeta) {
        //validacion.getValidacion(tarjetaDeDebito,numeroTarjeta.toString()," NO esta en nuestro sistema de Tarjetas");
        return tarjetaRepository.findByNumeroTarjetaDeDebito(numeroTarjeta);
    }

    @Override
    public TarjetaDeDebito saveTarjetaDeDebito(TarjetaDeDebitoVO tarjetaDeDebitoVO) {
        return saveTarjetaDeDebito(tarjetaDeDebitoVO.numeroTarjetaDeDebito,tarjetaDeDebitoVO.titular, tarjetaDeDebitoVO.dineroDisponibleMonedaLocal, tarjetaDeDebitoVO.monedaNacional,tarjetaDeDebitoVO.dineroDisponibleMonedaExtranjera,tarjetaDeDebitoVO.monedaExtranjera);
    }

    @Override
    public TarjetaDeDebito saveTarjetaDeDebito(String numeroTarjeta, String titular, float dineroDisponibleMonedaLocal, String monedaNacional, float dineroDisponibleMonedaExtranjera, String monedaExtranjera) {
        TarjetaDeDebito tarjetaDeDebito=getTarjetaDeDebitoByNumber(numeroTarjeta);
        validacion.getValidacionSiEstaEnElSistema(tarjetaDeDebito,numeroTarjeta.toString()," YA esta en nuestro sistema de Tarjetas");
        return tarjetaRepository.save(new TarjetaDeDebito(numeroTarjeta,titular.toUpperCase(),dineroDisponibleMonedaLocal,monedaNacional,dineroDisponibleMonedaExtranjera,monedaExtranjera));
    }
}
