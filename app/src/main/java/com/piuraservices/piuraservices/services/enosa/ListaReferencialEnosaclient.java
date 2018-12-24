package com.piuraservices.piuraservices.services.enosa;

import com.piuraservices.piuraservices.models.enosa.InfoReferencialEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgraumodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaReferencialEnosaclient {
    //lista todoas las entidades ok eps
    @GET("informacion/listainfoentidades")
    Call<List<InfoReferencialEnosamodel>> getInfoReferencialenosa();
}
