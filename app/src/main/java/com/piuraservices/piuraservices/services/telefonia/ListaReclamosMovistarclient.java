package com.piuraservices.piuraservices.services.telefonia;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaReclamosMovistarclient {
    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosClaro();

}

