package coba.daily.you.service;


import coba.daily.you.model.entity.Movies;
import org.springframework.stereotype.Service;

@Service
public interface MoviesService {
    Movies saveMoviesMaterDetail(Movies movies);
}
