package coba.daily.you.service.impl;

import coba.daily.you.model.entity.MoviesCategory;
import coba.daily.you.service.MoviesCategoryService;
import coba.daily.you.repository.MoviesCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesCategoryServiceImpl implements MoviesCategoryService {
    @Autowired
    private MoviesCategoryRepository moviesCategoryRepository;

    @Override
    public MoviesCategory saveProductCategoryMaterDetail(MoviesCategory moviesCategory){
     moviesCategory = moviesCategoryRepository.save(moviesCategory);
     return moviesCategory;
    }
}
