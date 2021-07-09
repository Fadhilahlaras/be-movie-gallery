package coba.daily.you.service.impl;

import coba.daily.you.model.entity.Movies;
import coba.daily.you.repository.MoviesCategoryRepository;
import coba.daily.you.repository.MoviesRepository;
import coba.daily.you.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesServiceImp implements MoviesService {
    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private MoviesCategoryRepository moviesCategoryRepository;

    @Override
    public Movies saveMoviesMaterDetail(Movies movies){
        movies = moviesRepository.save(movies);
        movies.setMoviesCategory(moviesCategoryRepository.findById(movies.getIdCategory()).get());
        return movies;
    }
}
