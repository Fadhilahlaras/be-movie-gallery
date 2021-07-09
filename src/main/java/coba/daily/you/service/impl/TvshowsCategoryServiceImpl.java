package coba.daily.you.service.impl;

import coba.daily.you.model.entity.TvshowsCategory;
import coba.daily.you.repository.TvshowsCategoryRepository;
import coba.daily.you.service.TvshowsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvshowsCategoryServiceImpl implements TvshowsCategoryService {
    @Autowired
    private TvshowsCategoryRepository tvshowsCategoryRepository;

    @Override
    public TvshowsCategory saveTvshowsCategoryMaterDetail(TvshowsCategory tvshowsCategory){
     tvshowsCategory = tvshowsCategoryRepository.save(tvshowsCategory);
     return tvshowsCategory;
    }
}
