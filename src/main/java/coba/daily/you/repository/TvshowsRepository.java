package coba.daily.you.repository;

import coba.daily.you.model.entity.Tvshows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvshowsRepository extends JpaRepository<Tvshows, Integer> {

    @Query(value = "SELECT * FROM t_tvshows where id_category =?1", nativeQuery = true)
    List<Tvshows> cariTvshowsCategory(Integer id);

    @Query(value = "SELECT * FROM t_tvshows where tvshows_name ~* ?1", nativeQuery = true)
    List<Tvshows> searchTvshows(String search);

    @Query(value = "select year from Tvshows where id = ?1", nativeQuery = false)
    Double getYearById(Integer id);

}
