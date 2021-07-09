package coba.daily.you.service;


import coba.daily.you.model.entity.MoviesCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public interface MoviesCategoryService {
    MoviesCategory saveProductCategoryMaterDetail (MoviesCategory moviesCategory);

}
