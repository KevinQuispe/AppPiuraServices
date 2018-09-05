package com.piuraservices.piuraservices.services.claro;

import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListaTramitesClaroclient {
    @GET("informacion/getInfoReclamos")
    Call<List<InfoReclamosEnosamodel>> getInfoReclamosClaro();

}
