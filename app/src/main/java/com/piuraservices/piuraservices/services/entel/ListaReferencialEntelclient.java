package com.piuraservices.piuraservices.services.entel;

import com.piuraservices.piuraservices.models.enosa.InfoReferencialEnosamodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoReferencialEntelmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaReferencialEntelclient {
    //lista todoas las entidades ok eps
    @GET("informacion/listainfoentidades")
    Call<List<InfoReferencialEntelmodel>> getInfoReferencialentel();

}
