package com.application.api.services.interfaces;

import com.application.api.vo.CotizacionVO;

public interface ICotizacionService {

    public CotizacionVO getCotizacion();

    public float valorCompraDolar();
}
