package coba.daily.you.controller.restapi;

import coba.daily.you.model.entity.TvshowsCategory;
import coba.daily.you.repository.TvshowsCategoryRepository;
import coba.daily.you.service.TvshowsCategoryService;
import coba.daily.you.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tvshowscategory")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiTvshowsCategory {
    @Autowired
    private TvshowsCategoryService tvshowsCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TvshowsCategoryRepository tvshowsCategoryRepository;

    @GetMapping()
    public ResponseEntity<List<TvshowsCategory>> getTvshowsCategories() {
        List<TvshowsCategory> body = tvshowsCategoryRepository.findAll();
        return new ResponseEntity<List<TvshowsCategory>>(body, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> editSaveTvshowsCategory(@RequestBody TvshowsCategory tvshowsCategory){
        if (Helper.notNull(readTvshowsCategory(tvshowsCategory.getCategoryTvshows()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        createTvshowsCategory(tvshowsCategory);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    public TvshowsCategory readTvshowsCategory(String categoryTvshows) {
        return tvshowsCategoryRepository.findByCategoryTvshows(categoryTvshows);
    }
    public void createTvshowsCategory(TvshowsCategory tvshowsCategory) {
        tvshowsCategoryRepository.save(tvshowsCategory);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        tvshowsCategoryRepository.deleteById(id);
    }

}
