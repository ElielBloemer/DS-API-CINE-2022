package com.application.api.controllers;

import com.application.api.model.evento.Pelicula;
import com.application.api.model.evento.Seleccion;
import com.application.api.services.interfaces.IPeliculaService;
import com.application.api.vo.PeliculaVO;
import com.application.api.vo.PeliculaVO2;
import com.application.api.vo.SeleccionVO;
import com.application.api.vo.SeleccionVO2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1.0/disenioDeSistemas/films")
@Tag(name="Pelicula controller",description = "controller created for manager the films")
@CrossOrigin(origins = "*")
@ApiResponses({
        @ApiResponse(responseCode = "400",description = "Bad Request"),
        @ApiResponse(responseCode = "404",description = "Not Found"),
        @ApiResponse(responseCode = "500",description = "Backend error")
})
public class PeliculaController {

    private final IPeliculaService iPeliculaService;

    public PeliculaController(IPeliculaService ipeliculaService){
        this.iPeliculaService=ipeliculaService;
    }

    @PostMapping
    @Operation(summary = "create a film")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "criado")
    })
    public ResponseEntity<PeliculaVO> guardarUnaPelicula(@RequestParam String nombrePelicula,
                                                         String nombreProductora,
                                                         Integer duracionPeliculaEnMinutos,
                                                         Integer CalificacionEvento,
                                                         Float precioEvento,
                                                         String identificacionSala){
        PeliculaVO peliculaVO= new PeliculaVO(nombrePelicula,nombreProductora, duracionPeliculaEnMinutos, CalificacionEvento,precioEvento,identificacionSala);
        iPeliculaService.validarInformacion(peliculaVO.nombrePelicula.toUpperCase());
        Pelicula pelicula = iPeliculaService.guardarPelicula(peliculaVO);
        PeliculaVO response = new PeliculaVO(pelicula);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("contieneActorEstrella/{nameFilm}")
    @Operation(summary = "check out if any actor is a star")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getPeliculaPorActorEstrella(@RequestParam String nameFilm){
        boolean contieneActorEstrella=iPeliculaService.contieneActorEstrella(nameFilm);
        return ResponseEntity.ok(nameFilm.toUpperCase() + " contiene algun ACTOR estrella? " + contieneActorEstrella);
    }

    @GetMapping("/{nameFilm}")
    @Operation(summary = "get a film by name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getPelicula(@RequestParam String nombrePelicula){
        Pelicula pelicula=iPeliculaService.getPeliculaByName(nombrePelicula);
        PeliculaVO2 response= new PeliculaVO2(pelicula);
        return ResponseEntity.ok(response);
    }

    @GetMapping("contieneProductoraFamosa/{nameFilm}")
    @Operation(summary = "check out if the Productura is famous")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getPeliculaSiProductoraIsFamous(@RequestParam String nameFilm){
        boolean contieneProductoraFamous=iPeliculaService.productoraEsFamosa(nameFilm);
        return ResponseEntity.ok(nameFilm.toUpperCase() + " esta producido por una Productora Famous? " + contieneProductoraFamous);
    }

    @GetMapping("isInteresting/{nameFilm}")
    @Operation(summary = "check out if a films is interesting")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok")
    })
    public ResponseEntity<Object>getPeliculaIfIsIsteresting(@RequestParam String nameFilm){
        boolean esInteresante=iPeliculaService.estaInteresante(nameFilm.toUpperCase());
        return ResponseEntity.ok(" la Pelicula "+nameFilm.toUpperCase() + " e interesante? " + esInteresante);
    }


}
