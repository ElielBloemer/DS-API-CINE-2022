package com.application.api.services.evento;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Pelicula;
import com.application.api.model.evento.Sala;
import com.application.api.model.evento.Seleccion;
import com.application.api.persistance.ActorRepository;
import com.application.api.persistance.PeliculaRepository;
import com.application.api.persistance.SalaRepository;
import com.application.api.services.interfaces.IPeliculaService;
import com.application.api.services.interfaces.IProductoraFamosa;
import com.application.api.services.validations.Validacion;
import com.application.api.vo.PeliculaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeliculaService implements IPeliculaService, IProductoraFamosa {
    private PeliculaRepository peliculaRepository;
    //private SalaRepository salaRepository;

    private List<Pelicula> peliculas;

    private final Validacion validacion = new Validacion();
    //private ActorRepository actorRepository;

    @Autowired
    public void setPeliculaService(PeliculaRepository peliculaRepository,SalaRepository salaRepository){
        this.peliculaRepository=peliculaRepository;
        //this.salaRepository=salaRepository;
        //peliculas=peliculaRepository.findAll();
    }
    //@Autowired
    //public void setActorRepository(ActorRepository actorRepository){
       // this.actorRepository=actorRepository;
    //}

    @Override
    public Pelicula getAllPeliculas() {
        //peliculas=peliculaRepository.findAll();
        return null;
    }

    @Override
    public Pelicula getPeliculaByName(String nombrePelicula) {
       //List<Pelicula> peliculasDB=new ArrayList<>();
        //peliculasDB=peliculas;
        Pelicula pelicula=peliculaRepository.findByNombrePelicula(nombrePelicula);
        peliculaExiste(pelicula,nombrePelicula.toUpperCase());
        //List<Actor> actores=actorRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        //pelicula.setElenco(existeActoresEnEsPelicula(actores));
        //Pelicula pelicula=peliculasDB.stream().filter(pelicula1 -> pelicula1.getNombrePelicula().equals(nombrePelicula.toUpperCase()));
        return pelicula;
    }

    @Override
    public Pelicula getPeliculaById(Integer idPelicula) {
        Pelicula pelicula=peliculaRepository.findById(idPelicula);
        // LA VALIDACION ES MEJOR HACER DONDE LLAMO
        //peliculaExiste(pelicula,idPelicula.toString());
        return pelicula;
    }

    @Override
    public void validarInformacion(String nombrePelicula) {
        if(nombrePelicula.equals("STRING")){
            throw new NotFoundException(" Nombre INVALIDO para la pelicula");
        }
    }

    @Override
    public Pelicula guardarPelicula(PeliculaVO peliculaVO) {
        return guardarPelicula(peliculaVO.nombrePelicula,null,peliculaVO.productora, peliculaVO.duracionPeliculaMinutosPelicula,peliculaVO.calificacionEvento,peliculaVO.precioEvento,peliculaVO.fechaEvento);
    }

    @Override
    public Pelicula guardarPelicula(String nombrePelicula, List<Actor> elenco, String productora, Integer duracionPelicula, Integer calificacionEvento, Float precioEvento, LocalDateTime fechaEvento) {
        Pelicula nuevaPelicula=peliculaRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        estaEnElSistema(nuevaPelicula,nombrePelicula);
       // Sala sala=salaRepository.findByIdentificacionSala(identificacionSala);
       // validacion.getValidacion(sala,"","Ops :(, the Sala isnt in the system, PLEASE to create the room first.");
       // validacion.estaSalaOcupada(sala);
      //  sala.setTieneEventoAsignado(true);
       // return peliculaRepository.save(new Pelicula(nombrePelicula.toUpperCase(),elenco,productora.toUpperCase(),duracionPelicula,calificacionEvento,precioEvento,sala));
        return peliculaRepository.save(new Pelicula(nombrePelicula.toUpperCase(),elenco,productora.toUpperCase(),duracionPelicula,calificacionEvento,precioEvento,fechaEvento,null,true));
    }

    @Override
    public boolean contieneActorEstrella(String nombrePelicula) {
        Pelicula pelicula=getPeliculaByName(nombrePelicula.toUpperCase());
        List<Actor> actores=pelicula.getElenco();
        for (Actor actor: actores) {
            if(actor.isEsEstrella()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean productoraEsFamosa(String nombrePelicula) {
        Pelicula pelicula=getPeliculaByName(nombrePelicula);
        List<String> productoras=getProductoraFamous();
        for (String productora: productoras) {
            if(pelicula.getProductora().equals(productora)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean interestingCriteria(String nombrePelicula) {
        Pelicula pelicula=peliculaRepository.findByNombrePelicula(nombrePelicula);
        peliculaExiste(pelicula,nombrePelicula);
        return productoraEsFamosa((pelicula.getNombrePelicula())) || (tieneMuchosMinutos(pelicula) && contieneActorEstrella(pelicula.getNombrePelicula()));
    }

    @Override
    public boolean estaInteresante(String nombrePelicula) {
        Pelicula pelicula=getPeliculaByName(nombrePelicula);
        return pelicula.getCalificacion()>=8 && interestingCriteria(nombrePelicula);
    }

    @Override
    public void uptadePeliculaWithSala(Pelicula pelicula,String idSala) {
     pelicula.setIdSala(idSala);
       peliculaRepository.save(pelicula);
    }

    @Override
    public void updateCleaningSala(String nombrePelicula) {

    }

    public boolean tieneMuchosMinutos(Pelicula pelicula){
        return pelicula.getDuracionPelicula() >= 200 && pelicula.getDuracionPelicula() <= 240;
    }

    private List<Actor> existeActoresEnEsPelicula(List<Actor> actores) {
        return actores;
    }

    public void peliculaExiste(Pelicula pelicula,String nombrePelicula){
        if(pelicula==null){
            throw new NotFoundException("La Pelicula com NOMBRE o ID "+ nombrePelicula.toUpperCase() + " NO existe en el sistema");
        }
    }

    public void estaEnElSistema(Pelicula pelicula, String nombre) {
        if (pelicula != null) {
            throw new NotFoundException("la pelicula " + nombre.toUpperCase() + " ya esta en el sistema!");
        }
    }

    @Override
    public List<String> getProductoraFamous() {
        return Arrays.asList("DISNEY","PIXAR","WARNER BROS","ORIGIN FILM");
    }
}
