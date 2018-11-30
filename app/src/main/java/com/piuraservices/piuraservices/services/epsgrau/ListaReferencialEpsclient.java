package com.piuraservices.piuraservices.services.epsgrau;

import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgraumodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListaReferencialEpsclient {

    //lista todoas las entidades ok eps
    @GET("informacion/listainfoentidades")
    Call<List<InfoReferencialEpsgraumodel>> getInfoReferencialeps();

    //lista todoas las entidades  con parametro
    @GET("informacion/listainfoentidad/{id}")
    Call<List<InfoReferencialEpsgraumodel>> getinfoentidad (@Query("id") long id);

    //lista todoas las entidades  con parametro no funtion
    @GET("informacion/listainfoentidad/{id}")
    Call<InfoReferencialEpsgraumodel> getInfoReferencial (@Path("id") long id);

    //@GET("findUser")
    //Call<ResponseService> findUser(@Query("id")int idUser);
}
