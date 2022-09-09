package com.application.api.services.cotizacion;


import com.application.api.services.interfaces.ICotizacionService;
import com.application.api.vo.CotizacionVO;
import com.application.api.vo.CotizacionVOValorCompra;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CotizacionService implements ICotizacionService {

    @Value("${api.cotizacion}")
    private String urlApi;

    @Override
    public CotizacionVO getCotizacion() {
        String url=urlApi;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url,String.class);
        JSONObject obj = new JSONObject(result);
        return new CotizacionVO(obj.getString("fecha"),obj.getString("compra"),obj.getString("venta"));
    }

    @Override
    public float valorCompraDolar() {
        CotizacionVO cotizacion=getCotizacion();
        return Float.parseFloat(cotizacion.compra);
    }

}
