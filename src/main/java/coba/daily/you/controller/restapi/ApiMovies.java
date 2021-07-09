package coba.daily.you.controller.restapi;

import coba.daily.you.model.dto.MoviesDto;
import coba.daily.you.model.entity.Movies;
import coba.daily.you.repository.MoviesRepository;
import coba.daily.you.service.MoviesCategoryService;
import coba.daily.you.service.MoviesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;

@RestController
@RequestMapping("/api/movies")
public class ApiMovies {

    @Autowired
    private MoviesService moviesService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MoviesCategoryService moviesCategoryService;

    @Autowired
    private MoviesRepository moviesRepository;

    @GetMapping()
    public ResponseEntity<List<MoviesDto>> getListMoviess() {
        List<Movies> moviesList = moviesRepository.findAll();
        List<MoviesDto> moviesDtos = moviesList.stream().map(movies -> mapMoviesToMoviesDto(movies)).collect(Collectors.toList());
        return new ResponseEntity<List<MoviesDto>>(moviesDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoviesDto> getMovies(@PathVariable Integer id) {
        Movies movies = moviesRepository.findById(id).get();
        MoviesDto moviesDto= new MoviesDto();
        modelMapper.map(movies, moviesDto);
        modelMapper.map(movies.getMoviesCategory(), moviesDto);
        moviesDto.setId(movies.getId());
        return new ResponseEntity<MoviesDto>(moviesDto, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<MoviesDto>> getMoviess(@PathVariable Integer id) {
        List<MoviesDto> body = listMoviesByCategory(id);
        return new ResponseEntity<List<MoviesDto>>(body, HttpStatus.OK);
    }

    public List<MoviesDto> listMoviesByCategory(Integer id) {
        List<Movies> moviess = moviesRepository.cariMoviesCategory(id);
        List<MoviesDto> moviesDtos = moviess.stream().map(movies -> mapMoviesToMoviesDto(movies)).collect(Collectors.toList());

        return moviesDtos;
    }

    @GetMapping("/find/{movies}")
    public ResponseEntity<List<MoviesDto>> getMoviess(@PathVariable String movies) {
//        String search= "\\y" +product+"\\y";
        List<MoviesDto> body = searchMovies(movies);
        return new ResponseEntity<List<MoviesDto>>(body, HttpStatus.OK);
    }

    public List<MoviesDto> searchMovies(String search) {
        List<Movies> moviess = moviesRepository.searchMovies(search);
        List<MoviesDto> moviesDtos = moviess.stream().map(movies -> mapMoviesToMoviesDto(movies)).collect(Collectors.toList());

        return moviesDtos;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value="/save")
    public MoviesDto editSaveMovies(@RequestPart(value="data", required = true) MoviesDto moviesDto, @RequestPart(value="pictureUrl", required = false) MultipartFile file) throws Exception{
        Movies movies = modelMapper.map(moviesDto, Movies.class);

        if (file==null){
            movies.setPictureUrl(moviesRepository.findById(moviesDto.getId()).get().getPictureUrl());
        } else {
            String userFolderPath = "D:/Laras/upload/";
            Path path = Paths.get(userFolderPath);
            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            movies.setPictureUrl(file.getOriginalFilename());
        }

        movies.setIdCategory(moviesDto.getIdCategory());
        movies = moviesService.saveMoviesMaterDetail(movies);
        MoviesDto moviesDtoDB = mapMoviesToMoviesDto(movies);

        return moviesDtoDB;
    }

    private MoviesDto mapMoviesToMoviesDto(Movies movies){
        MoviesDto moviesDto = modelMapper.map(movies, MoviesDto.class);
        moviesDto.setIdCategory(movies.getMoviesCategory().getId());
        moviesDto.setCategoryMovies(movies.getMoviesCategory().getCategoryMovies());
        moviesDto.setId(movies.getId());
        return moviesDto;
    }


    @GetMapping("/getImage/{id}")
    public String getImage(@PathVariable Integer id) throws IOException {
        Movies movies = moviesRepository.findById(id).get();
        String userFolderPath = "D:/Laras/upload/";
        String pathFile = userFolderPath + movies.getPictureUrl();
        Path paths = Paths.get(pathFile);
        byte[] foto = Files.readAllBytes(paths);
        String img = Base64.getEncoder().encodeToString(foto);
        return img;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        moviesRepository.deleteById(id);
    }

}
