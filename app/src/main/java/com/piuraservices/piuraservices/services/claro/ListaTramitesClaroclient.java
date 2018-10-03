package com.piuraservices.piuraservices.services.claro;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReclamosClaromodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoTramitesClaromodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaTramitesClaroclient {

    @GET("informacion/getInfoReclamos")
    Call<List<InfoTramitesClaromodel>> getInfoTrmitesClaro();

    @GET("informacion/listainforeclamos/{id}")

    Call<InfoTramitesClaromodel> getInfoTramitesClaro(@Path("id") long id);
}
