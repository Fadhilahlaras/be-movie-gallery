package coba.daily.you.repository;

import coba.daily.you.model.entity.MoviesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesCategoryRepository extends JpaRepository<MoviesCategory, Integer> {
    MoviesCategory findByCategoryMovies(String categoryMovies);
}
