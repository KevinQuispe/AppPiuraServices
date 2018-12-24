package com.piuraservices.piuraservices.services.claro;

import com.piuraservices.piuraservices.models.telefonia.claro.InfoReferencialClaromodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoReferencialMovistarmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaReferencialClaroclient {
    @GET("informacion/listainfoentidades")
    Call<List<InfoReferencialClaromodel>> getInfoReferencialclaro();

}
