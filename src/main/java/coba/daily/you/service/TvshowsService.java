package coba.daily.you.service;

import coba.daily.you.model.entity.Tvshows;
import org.springframework.stereotype.Service;


@Service
public interface TvshowsService {
    Tvshows saveTvshowsMaterDetail(Tvshows tvshows);
}
