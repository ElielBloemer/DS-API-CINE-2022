package com.application.api.model.apicotizacion;

import lombok.*;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Cotizacion {

   // private int id;
    private String fecha;
    private String compra;
    private String venta;

    public String toString(){
        return "Cotizacion Dolar Blue\n\n" + "Fecha: "+this.fecha+"\nDolar venta: "+this.venta+"\n"
                +"Dolar compra: "+this.compra +"\n\n"+"El valor del dolar que debes usar como referencia es: "+this.compra+"\n";
    }

    public String obtenerCotizacion(){
        String uri="https://api-dolar-argentina.herokuapp.com/api/dolarblue/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        /*JSONObject obj = new JSONObject(result);
        Cotizacion dolarBlue = new Cotizacion(obj.getString("fecha"),obj.getString("compra"),obj.getString("venta"));*/
        return result;
    }
}
