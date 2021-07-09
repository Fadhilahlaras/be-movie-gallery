package coba.daily.you.service;

import coba.daily.you.model.entity.TvshowsCategory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface TvshowsCategoryService {
    TvshowsCategory saveTvshowsCategoryMaterDetail (TvshowsCategory tvshowsCategory);

}
