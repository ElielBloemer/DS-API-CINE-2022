package com.application.api.services.tarjetadedebito;

import com.application.api.model.tarjetadedebito.TarjetaDeDebito;
import com.application.api.persistance.TarjetaRepository;
import com.application.api.services.interfaces.ICotizacionService;
import com.application.api.services.interfaces.ITarjetaDeDebitoService;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.TarjetaDeDebitoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaDeDebitoService implements ITarjetaDeDebitoService {

    private TarjetaRepository tarjetaRepository;
    private final Validacion validacion = new Validacion();

    private final ICotizacionService cotizacionService;

    public TarjetaDeDebitoService(ICotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @Autowired
    public void setTarjetaRepository(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    @Override
    public boolean pagar(float valorAPagar, String monedaElegida, String numeroTarjeta) {
        TarjetaDeDebito tarjetaDeDebito = getTarjetaDeDebitoByNumber(numeroTarjeta);
        validacion.getValidacion(tarjetaDeDebito, numeroTarjeta, " NO esta en nuestro sistema de Tarjetas");
        if (monedaElegida==null) {
            pagarConMonedaLocal(tarjetaDeDebito,valorAPagar);
            return true;
        } else {
            pagarConMonedaExtranjera(tarjetaDeDebito, valorAPagar);
          return true;
        }
    }

    @Override
    public TarjetaDeDebito getTarjetaDeDebitoByNumber(String numeroTarjeta) {
        return tarjetaRepository.findByNumeroTarjetaDeDebito(numeroTarjeta);
    }

    @Override
    public TarjetaDeDebito saveTarjetaDeDebito(TarjetaDeDebitoVO tarjetaDeDebitoVO) {
        return saveTarjetaDeDebito(tarjetaDeDebitoVO.numeroTarjetaDeDebito, tarjetaDeDebitoVO.titular, tarjetaDeDebitoVO.dineroDisponibleMonedaLocal, tarjetaDeDebitoVO.dineroDisponibleMonedaExtranjera, tarjetaDeDebitoVO.monedaExtranjera);
    }

    @Override
    public TarjetaDeDebito saveTarjetaDeDebito(String numeroTarjeta, String titular, float dineroDisponibleMonedaLocal, float dineroDisponibleMonedaExtranjera, String monedaExtranjera) {
        TarjetaDeDebito tarjetaDeDebito = getTarjetaDeDebitoByNumber(numeroTarjeta);
        validacion.getValidacionSiEstaEnElSistema(tarjetaDeDebito, numeroTarjeta.toString(), " YA esta en nuestro sistema de Tarjetas");
        return tarjetaRepository.save(new TarjetaDeDebito(numeroTarjeta, titular.toUpperCase(), dineroDisponibleMonedaLocal, "PESOS", dineroDisponibleMonedaExtranjera, monedaExtranjera.toUpperCase()));
    }

    @Override
    public boolean pagarConMonedaExtranjera(TarjetaDeDebito tarjetaDeDebito, float deudaAPagar) {
        float valorCompra = cotizacionService.valorCompraDolar();
        float deudaEnMonedaExtranjera;
        deudaEnMonedaExtranjera = deudaAPagar/valorCompra;
        if (tarjetaDeDebito.getMonedaExtranjera().equals("DOLAR")) {
            if (tarjetaDeDebito.getDineroDisponibleMonedaExtranjera() >= deudaEnMonedaExtranjera){
                tarjetaDeDebito.setDineroDisponibleMonedaExtranjera(tarjetaDeDebito.getDineroDisponibleMonedaExtranjera()-deudaEnMonedaExtranjera);
                tarjetaRepository.save(tarjetaDeDebito);
                return true;
            }else{
                validacion.getValidacion(null, " ", " Saldo en moneda extranjera INDISPONIBLE.");
                return false;
            }
        }
        validacion.getValidacion(null, "DOLAR", " Moneda NO existe en tu tarjeta.");
        return false;
    }

    @Override
    public boolean pagarConMonedaLocal(TarjetaDeDebito tarjetaDeDebito, float deudaAPagar) {

        if (tarjetaDeDebito.getDineroDisponibleMonedaLocal() >= deudaAPagar) {
            tarjetaDeDebito.setDineroDisponibleMonedaLocal(tarjetaDeDebito.getDineroDisponibleMonedaLocal() - deudaAPagar);
            tarjetaRepository.save(tarjetaDeDebito);
            return true;
        } else {
            //tarjetaDeDebito.setDineroDisponibleMonedaLocal(tarjetaDeDebito.getDineroDisponibleMonedaLocal() - valorAPagar);
            //tarjetaRepository.save(tarjetaDeDebito);
            validacion.getValidacion(null, " ", " Saldo INDISPONIBLE.");
            return false;
        }
    }
}
