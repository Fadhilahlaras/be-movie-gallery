package coba.daily.you.service.impl;

import coba.daily.you.model.entity.Tvshows;
import coba.daily.you.repository.TvshowsCategoryRepository;
import coba.daily.you.repository.TvshowsRepository;
import coba.daily.you.service.TvshowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvshowsServiceImp implements TvshowsService {
    @Autowired
    private TvshowsRepository tvshowsRepository;

    @Autowired
    private TvshowsCategoryRepository tvshowsCategoryRepository;

    @Override
    public Tvshows saveTvshowsMaterDetail(Tvshows tvshows){
        tvshows = tvshowsRepository.save(tvshows);
        tvshows.setTvshowsCategory(tvshowsCategoryRepository.findById(tvshows.getIdCategory()).get());
        return tvshows;
    }
}
