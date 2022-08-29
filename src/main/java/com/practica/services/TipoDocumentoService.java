package com.practica.services;

import com.practica.dto.request.TipoDocumentoRequest;

import java.util.List;

public interface TipoDocumentoService {
    public List<TipoDocumentoRequest> allTipoDocumento();
    public TipoDocumentoRequest oneTipoDocumento(Long id);

    public TipoDocumentoRequest createTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest);

    public TipoDocumentoRequest updateTipoDocumento(TipoDocumentoRequest tipoDocumentoRequest, Long id);

    public void deleteTipoDocumento(Long id);
}
