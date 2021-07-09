package coba.daily.you.controller.restapi;

import coba.daily.you.model.dto.TvshowsDto;
import coba.daily.you.model.entity.Tvshows;
import coba.daily.you.repository.TvshowsRepository;
import coba.daily.you.service.TvshowsCategoryService;
import coba.daily.you.service.TvshowsService;
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
@RequestMapping("/api/tvshows")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiTvshows {

    @Autowired
    private TvshowsService tvshowsService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TvshowsCategoryService tvshowsCategoryService;

    @Autowired
    private TvshowsRepository tvshowsRepository;

    @GetMapping()
    public ResponseEntity<List<TvshowsDto>> getListTvshows() {
        List<Tvshows> tvshowsList = tvshowsRepository.findAll();
        List<TvshowsDto> tvshowsDtos = tvshowsList.stream().map(tvshows -> mapTvshowsToTvshowsDto(tvshows)).collect(Collectors.toList());
//        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<TvshowsDto>>(tvshowsDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvshowsDto> getTvshows(@PathVariable Integer id) {
        Tvshows tvshows = tvshowsRepository.findById(id).get();
        TvshowsDto tvshowsDto = new TvshowsDto();
        modelMapper.map(tvshows, tvshowsDto);
        modelMapper.map(tvshows.getTvshowsCategory(), tvshowsDto);
        tvshowsDto.setId(tvshows.getId());
        return new ResponseEntity<TvshowsDto>(tvshowsDto, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<TvshowsDto>> getTvshowss(@PathVariable Integer id) {
        List<TvshowsDto> body = listTvshowsByCategory(id);
        return new ResponseEntity<List<TvshowsDto>>(body, HttpStatus.OK);
    }

    public List<TvshowsDto> listTvshowsByCategory(Integer id) {
        List<Tvshows> tvshowss = tvshowsRepository.cariTvshowsCategory(id);
        List<TvshowsDto> tvshowsDtos = tvshowss.stream().map(tvshows -> mapTvshowsToTvshowsDto(tvshows)).collect(Collectors.toList());

        return tvshowsDtos;
    }

    @GetMapping("/find/{tvshows}")
    public ResponseEntity<List<TvshowsDto>> getTvshowss(@PathVariable String tvshows) {
//        String search= "\\y" +product+"\\y";
        List<TvshowsDto> body = searchTvshows(tvshows);
        return new ResponseEntity<List<TvshowsDto>>(body, HttpStatus.OK);
    }

    public List<TvshowsDto> searchTvshows(String search) {
        List<Tvshows> tvshowss = tvshowsRepository.searchTvshows(search);
        List<TvshowsDto> tvshowsDtos = tvshowss.stream().map(tvshows -> mapTvshowsToTvshowsDto(tvshows)).collect(Collectors.toList());

        return tvshowsDtos;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value="/save")
    public TvshowsDto editSaveTvshows(@RequestPart(value="data", required = true) TvshowsDto tvshowsDto, @RequestPart(value="pictureUrl", required = false) MultipartFile file) throws Exception{
        Tvshows tvshows = modelMapper.map(tvshowsDto, Tvshows.class);

        if (file==null){
            tvshows.setPictureUrl(tvshowsRepository.findById(tvshowsDto.getId()).get().getPictureUrl());
        } else {
            String userFolderPath = "D:/Laras/upload/";
            Path path = Paths.get(userFolderPath);
            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            tvshows.setPictureUrl(file.getOriginalFilename());
        }

        tvshows.setIdCategory(tvshowsDto.getIdCategory());
        tvshows = tvshowsService.saveTvshowsMaterDetail(tvshows);
        TvshowsDto tvshowsDtoDB = mapTvshowsToTvshowsDto(tvshows);

        return tvshowsDtoDB;
    }

    private TvshowsDto mapTvshowsToTvshowsDto(Tvshows tvshows){
        TvshowsDto tvshowsDto = modelMapper.map(tvshows, TvshowsDto.class);
        tvshowsDto.setIdCategory(tvshows.getTvshowsCategory().getId());
        tvshowsDto.setCategoryTvshows(tvshows.getTvshowsCategory().getCategoryTvshows());
        tvshowsDto.setId(tvshows.getId());
        return tvshowsDto;
    }


    @GetMapping("/getImage/{id}")
    public String getImage(@PathVariable Integer id) throws IOException {
        Tvshows tvshows = tvshowsRepository.findById(id).get();
        String userFolderPath = "D:/Laras/upload/";
        String pathFile = userFolderPath + tvshows.getPictureUrl();
        Path paths = Paths.get(pathFile);
        byte[] foto = Files.readAllBytes(paths);
        String img = Base64.getEncoder().encodeToString(foto);
        return img;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        tvshowsRepository.deleteById(id);
    }

}
