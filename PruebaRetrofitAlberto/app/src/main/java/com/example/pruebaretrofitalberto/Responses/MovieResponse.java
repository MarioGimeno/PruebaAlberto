package com.example.pruebaretrofitalberto.Responses;

import java.util.List;

public class MovieResponse {
    public int page;
    public List<Movie> results;

    public static class Movie {
        public int id;
        public String title;
        public String overview;
    }
}
