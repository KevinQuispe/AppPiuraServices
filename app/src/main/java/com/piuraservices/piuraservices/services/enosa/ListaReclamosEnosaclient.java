package com.piuraservices.piuraservices.services.enosa;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListaReclamosEnosaclient {

    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosenosa();

    @GET("informacion/getinforeclamos/{id}")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosenosa( @Path("id") long id);
}
