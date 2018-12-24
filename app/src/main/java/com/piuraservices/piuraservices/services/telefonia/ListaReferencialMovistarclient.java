package com.piuraservices.piuraservices.services.telefonia;

import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoReferencialMovistarmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaReferencialMovistarclient {
    //lista todoas las entidades ok eps
    @GET("informacion/listainfoentidades")
    Call<List<InfoReferencialMovistarmodel>> getInfoReferencialmovistar();

}
