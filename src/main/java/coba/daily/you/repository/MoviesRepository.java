package coba.daily.you.repository;

import coba.daily.you.model.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MoviesRepository extends JpaRepository<Movies, Integer> {


    @Query(value = "SELECT * FROM t_movies where id_category =?1", nativeQuery = true)
    List<Movies> cariMoviesCategory(Integer id);

    @Query(value = "SELECT * FROM t_movies where movies_name ~* ?1", nativeQuery = true)
    List<Movies> searchMovies(String search);

    @Query(value = "select year from Movies where id = ?1", nativeQuery = false)
    Double getYearById(Integer id);

}
