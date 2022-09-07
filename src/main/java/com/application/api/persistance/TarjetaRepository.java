package com.application.api.persistance;

import com.application.api.model.tarjetadedebito.TarjetaDeDebito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<TarjetaDeDebito,Long> {

    public TarjetaDeDebito findByNumeroTarjetaDeDebito(String numeroTarjeta);
}
