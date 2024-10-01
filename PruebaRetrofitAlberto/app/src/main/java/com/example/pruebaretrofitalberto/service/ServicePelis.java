package com.example.pruebaretrofitalberto.service;

import com.example.pruebaretrofitalberto.MovieDetail;
import com.example.pruebaretrofitalberto.Responses.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicePelis {

    // Obtener las películas populares
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    // Buscar películas
    @GET("search/movie")
    Call<MovieResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page
    );

    // Obtener detalles de una película
    @GET("movie/{movie_id}")
    Call<MovieDetail> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
