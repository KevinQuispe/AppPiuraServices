package com.piuraservices.piuraservices.services.epsgrau;

import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListaReclamosEpsclient {

    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEpsgraumodel>> getInfoReclamoseps();
    //Call<UsuarioResponse> getUsuario(@Query("dni")String dni);

    @GET("informacion/listainforeclamos/{id}")
    Call<List<InfoReclamosEpsgraumodel>> getInfoReclamoseps( @Path("id") long id);

    @GET("informacion/listainforeclamos/{id}")
    Call<InfoReclamosEpsgraumodel> getInfoReclamosepsgrau();

}
