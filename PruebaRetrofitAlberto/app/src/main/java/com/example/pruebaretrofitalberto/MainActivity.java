package com.example.pruebaretrofitalberto;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebaretrofitalberto.Responses.MovieResponse;
import com.example.pruebaretrofitalberto.RetrofitClient.RetrofitClient;
import com.example.pruebaretrofitalberto.service.ServicePelis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;  // Asegúrate de importar Log

public class MainActivity extends AppCompatActivity {
    private final String API_KEY = "abeb5694268b4c076a67d6a808767acf"; // Reemplaza con tu API Key


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPopular = findViewById(R.id.btnPopular);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnDetails = findViewById(R.id.btnDetails);

        ServicePelis service = RetrofitClient.getTMDbService();

        btnPopular.setOnClickListener(v -> {
            Log.d("Retrofit", "Fetching popular movies...");
            service.getPopularMovies(API_KEY, "es-ES", 1).enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    Log.d("Retrofit", "Received response for popular movies");
                    if (response.isSuccessful()) {
                        MovieResponse movieResponse = response.body();
                        if (movieResponse != null && !movieResponse.results.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Primera Película: " + movieResponse.results.get(0).title, Toast.LENGTH_LONG).show();
                            Log.d("Retrofit", "Movie title: " + movieResponse.results.get(0).title);
                        } else {
                            Log.d("Retrofit", "MovieResponse is empty or null");
                        }
                    } else {
                        Log.d("Retrofit", "Response code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e("Retrofit", "Failed to fetch popular movies", t);
                    Toast.makeText(MainActivity.this, "Error al cargar películas populares", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnSearch.setOnClickListener(v -> {
            Log.d("Retrofit", "Searching for movies...");
            service.searchMovies(API_KEY, "es-ES", "titanic", 1).enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    Log.d("Retrofit", "Received response for movie search");
                    if (response.isSuccessful()) {
                        MovieResponse movieResponse = response.body();
                        if (movieResponse != null && !movieResponse.results.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Resultados para 'Titanic': " + movieResponse.results.get(0).title, Toast.LENGTH_LONG).show();
                            Log.d("Retrofit", "Movie search result: " + movieResponse.results.get(0).title);
                        } else {
                            Log.d("Retrofit", "Search response is empty or null");
                        }
                    } else {
                        Log.d("Retrofit", "Search response code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e("Retrofit", "Failed to search for movies", t);
                    Toast.makeText(MainActivity.this, "Error al buscar películas", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnDetails.setOnClickListener(v -> {
            Log.d("Retrofit", "Fetching movie details...");
            service.getMovieDetails(550, API_KEY, "es-ES").enqueue(new Callback<MovieDetail>() {
                @Override
                public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                    Log.d("Retrofit", "Received response for movie details");
                    if (response.isSuccessful()) {
                        MovieDetail movieDetail = response.body();
                        if (movieDetail != null) {
                            Toast.makeText(MainActivity.this, "Detalles de Fight Club: " + movieDetail.title, Toast.LENGTH_LONG).show();
                            Log.d("Retrofit", "Movie details: " + movieDetail.title);
                        } else {
                            Log.d("Retrofit", "MovieDetail is null");
                        }
                    } else {
                        Log.d("Retrofit", "Response code for movie details: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<MovieDetail> call, Throwable t) {
                    Log.e("Retrofit", "Failed to fetch movie details", t);
                    Toast.makeText(MainActivity.this, "Error al obtener detalles de la película", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}

