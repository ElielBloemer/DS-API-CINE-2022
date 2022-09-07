package com.application.api.model.apicotizacion;

import lombok.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/*@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data*/
@Service
@Getter
@Setter
public class Cotizacion {

    /*public String fecha;
    public String compra;
    public String venta;

    @Value("${api.cotizacion}")
    private String urlApi;

    public String toString(){
        return "Cotizacion Dolar Blue\n\n" + "Fecha: "+this.fecha+"\nDolar venta: "+this.venta+"\n"
                +"Dolar compra: "+this.compra +"\n\n"+"El valor del dolar que debes usar como referencia es: "+this.compra+"\n";
    }

    public Cotizacion(String fecha, String compra, String venta) {
        this.fecha = fecha;
        this.compra = compra;
        this.venta = venta;
    }

    public Cotizacion obtenerCotizacion(){
        String url=urlApi;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url,String.class);
        JSONObject obj = new JSONObject(result);
        Cotizacion dolarBlue = new Cotizacion(obj.getString("fecha"),obj.getString("compra"),obj.getString("venta"));
        return dolarBlue;
    }

    public Cotizacion() {
    }

    public Integer obtenerValorCompra(){
        String url=urlApi;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url,String.class);
        JSONObject obj = new JSONObject(result);
        return Integer.valueOf(obj.getString("compra"));
    }*/
}
