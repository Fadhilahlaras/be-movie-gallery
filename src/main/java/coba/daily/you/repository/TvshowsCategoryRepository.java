package coba.daily.you.repository;

import coba.daily.you.model.entity.TvshowsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvshowsCategoryRepository extends JpaRepository<TvshowsCategory, Integer> {
    TvshowsCategory findByCategoryTvshows(String categoryTvShows);
}
