package coba.daily.you.controller.restapi;


import coba.daily.you.model.entity.MoviesCategory;
import coba.daily.you.repository.MoviesCategoryRepository;
import coba.daily.you.service.MoviesCategoryService;
import coba.daily.you.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moviescategory")
public class ApiMoviesCategory {
    @Autowired
    private MoviesCategoryService moviesCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoviesCategoryRepository moviesCategoryRepository;

    @GetMapping()
    public ResponseEntity<List<MoviesCategory>> getMoviesCategories() {
        List<MoviesCategory> body = moviesCategoryRepository.findAll();
        return new ResponseEntity<List<MoviesCategory>>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> editSaveMoviesCategory(@RequestBody MoviesCategory moviesCategory){
        if (Helper.notNull(readMoviesCategory(moviesCategory.getCategoryMovies()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        createMoviesCategory(moviesCategory);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    public MoviesCategory readMoviesCategory(String categoryMovies) {
        return moviesCategoryRepository.findByCategoryMovies(categoryMovies);
    }
    public void createMoviesCategory(MoviesCategory moviesCategory) {
        moviesCategoryRepository.save(moviesCategory);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        moviesCategoryRepository.deleteById(id);
    }

}
