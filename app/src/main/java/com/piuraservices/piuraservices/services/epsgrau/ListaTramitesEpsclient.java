package com.piuraservices.piuraservices.services.epsgrau;

import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaTramitesEpsclient {

    @GET("informacion/getInfoTramites")
    Call<List<InfoTramitesEpsgraumodel>> getInfoTramiteseps();
}
