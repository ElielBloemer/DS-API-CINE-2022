package com.application.api.controllers;

import com.application.api.services.interfaces.ICotizacionService;
import com.application.api.vo.CotizacionVO;
import com.application.api.vo.CotizacionVOValorCompra;
import com.application.api.vo.TarjetaDeDebitoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/V1.0/disenioDeSistemas")
@Tag(name = "Api controller",description = "CONTROLLER CRIADO PARA GESTIONAR LA COTIZACION DEL DOLAR BLUE")
@CrossOrigin(origins = "*")
@ApiResponses({// aqui e digo os tipos de erros que podem devolver
        @ApiResponse(responseCode =  "400",description = "bad request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend Error")
})
public class ApiCotizacionController {

    private final ICotizacionService cotizacionService;

    public ApiCotizacionController(ICotizacionService cotizacionService) {
        this.cotizacionService=cotizacionService;
    }

    @GetMapping(value = "/cotizacion/dolarblue")
    @Operation(summary= "trae la cotizacion del dolar blue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cotizacion entregue")
    })
    private ResponseEntity<Object> getCotizacionDolar(){
        return ResponseEntity.ok(cotizacionService.getCotizacion());
    }

    @GetMapping(value = "/cotizacion/onlyPaydolarblue")
    @Operation(summary= "trae la cotizacion de compra del dolar blue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cotizacion entregue")
    })
    private ResponseEntity<Object> getCotizacionCompraDolar(){
        float valorCompra= cotizacionService.valorCompraDolar();
        CotizacionVOValorCompra cotizacionVOValorCompra= new CotizacionVOValorCompra(valorCompra);
        return ResponseEntity.ok(cotizacionVOValorCompra);
    }
}
