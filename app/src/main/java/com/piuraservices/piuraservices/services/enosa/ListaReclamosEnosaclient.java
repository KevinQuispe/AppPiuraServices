package com.piuraservices.piuraservices.services.enosa;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaReclamosEnosaclient {

    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosenosa();
}
