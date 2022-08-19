package com.application.api.controllers;

import com.application.api.model.apicotizacion.Cotizacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/V1.0/disenioDeSistemas")
@Tag(name = "Api controller",description = "Controller criado para gestionar la cotizacion del Dolar")
@CrossOrigin(origins = "*")
@ApiResponses({// aqui e digo os tipos de erros que podem devolver
        @ApiResponse(responseCode =  "400",description = "bad request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend Error")
})
public class ApiCotizacionController {

    private Cotizacion cotizacion;

    @GetMapping(value = "/cotizacion/dolarblue")
    @Operation(summary= "trae la cotizacion del dolar blue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " cotizacion entregue")
    })
    private String getCotizacionDolar(){
        String uri="https://api-dolar-argentina.herokuapp.com/api/dolarblue/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        /*JSONObject obj = new JSONObject(result);
        Cotizacion dolarBlue = new Cotizacion(obj.getString("fecha"),obj.getString("compra"),obj.getString("venta"));*/
        return result;
    }

}
