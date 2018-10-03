package com.piuraservices.piuraservices.services.epsgrau;

import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ListaTramitesEpsclient {

    @GET("informacion/getInfoTramites")
    Call<List<InfoTramitesEpsgraumodel>> getInfoTramiteseps();

    @GET("informacion/listainfotramites/{id}")
    Call<List<InfoTramitesEpsgraumodel>> getInfoTramiteseps (@Path("id") long id);

}
