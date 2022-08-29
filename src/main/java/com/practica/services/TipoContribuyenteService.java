package com.practica.services;

import com.practica.dto.request.TipoContribuyenteRequest;

import java.util.List;

public interface TipoContribuyenteService {

    public List<TipoContribuyenteRequest> allTipoContribuyente();
    public TipoContribuyenteRequest oneTipoContribuyente(Long id);

    public TipoContribuyenteRequest createTipoContribuyente(TipoContribuyenteRequest tipoContribuyenteRequest);

    public TipoContribuyenteRequest updateTipoContribuyente(TipoContribuyenteRequest tipoContribuyenteRequest, Long id);

    public void deleteTipoContribuyente(Long id);
}
