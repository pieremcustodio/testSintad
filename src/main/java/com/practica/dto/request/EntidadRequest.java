package com.practica.dto.request;

import com.practica.models.Entidad;
import com.practica.models.TipoContribuyente;
import com.practica.models.TipoDocumento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class EntidadRequest {
    private long id;
    @NotBlank
    @Size(min = 8, max = 25)
    private String nroDocumento;

    @NotBlank
    @Size(max = 100)
    private String razonSocial;

    @Size(max = 100)
    private String nombreComercial;

    private String direccion;

    @Size(max = 50)
    private String telefono;

    @NotNull
    private int idTipoDocumento;

    private int idTipoContribuyente;

    private boolean estado=true;
}
