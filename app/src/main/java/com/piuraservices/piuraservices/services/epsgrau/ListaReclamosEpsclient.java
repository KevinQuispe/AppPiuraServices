package com.piuraservices.piuraservices.services.epsgrau;

import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaReclamosEpsclient {
    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEpsgraumodel>> getInfoReclamoseps();
}
