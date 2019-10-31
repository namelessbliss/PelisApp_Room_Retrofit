package com.nb.pelisapp.data.remote;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {


    /**
     * Intercepta cualquier request que se lance
     *
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        //Concatena a la url el parametro api_key
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", ApiConstants.API_KEY)
                .build();
        //Forma un nuevo reques con la nueva url
        Request request = originalRequest.newBuilder()
                .url(url)
                .build();

        //Retorna el nuevo request formado
        return chain.proceed(request);
    }
}
