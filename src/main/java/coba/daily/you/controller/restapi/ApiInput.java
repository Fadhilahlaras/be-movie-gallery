package coba.daily.you.controller.restapi;

import coba.daily.you.model.dto.InputDto;
import coba.daily.you.model.entity.Input;
import coba.daily.you.repository.InputRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiInput {
    @Autowired
    private InputRepository inputRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ApiInput(InputRepository inputRepository, ModelMapper modelMapper) {
        this.inputRepository = inputRepository;
        this.modelMapper = modelMapper;
    }

//    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping() //Get data return inputDTOList
    public List<InputDto> getList() {
        //Create container to contain array input dari repo input
        List<Input> inputList = inputRepository.findAll();
        //Create container
        List<InputDto> inputDtoList =
                inputList.stream()
                        .map(in -> mapInputToInputDto(in))
                        .collect(Collectors.toList());

        return inputDtoList;
    }

    private InputDto mapInputToInputDto(Input input) {
        InputDto inputDTO = modelMapper.map(input, InputDto.class);
        return inputDTO;
    }


//    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/getImage/{id}") //Get Image sesuai ID-nya
    public String getImage(@PathVariable Integer id) throws IOException {
        //Create object untuk menampung data dari DB.
        Input input = inputRepository.findById(id).get();
        //retrieve data from computer folder
        String userFolderPath = "D:/Laras/upload/";
        //String Alamat File Image
        String pathFile = userFolderPath + input.getPictureUrl();
        //Type Paths
        Path paths = Paths.get(pathFile);
        //Convert type paths ke byte
        byte[] foto = Files.readAllBytes(paths);
        //Ubah ke string base64 untuk handle image
        String img = Base64.getEncoder().encodeToString(foto);

        return img;
    }


//    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/save")
    //request suatu object yg type nya Media Type -> Multipart_Form_Data_Form
    public InputDto editSaveData(@RequestPart(value="data", required = true) InputDto inputDTO, @RequestPart(value="pictureUrl", required = false) MultipartFile file) throws Exception {
        //Mapping Data Request Part
        Input input = modelMapper.map(inputDTO, Input.class);

        if (file == null){
            input.setPictureUrl(inputRepository.findById(inputDTO.getId()).get().getPictureUrl());
        } else {
            //Save Photo
            String userFolderPath = "D:/Laras/upload/";
            Path path = Paths.get(userFolderPath);
            //set nama photo yg di save
            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            input.setPictureUrl(file.getOriginalFilename());
        }

        input = inputRepository.save(input);
        InputDto inputDto1 = mapInputToInputDto(input);
        return inputDto1;
    }

//    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public InputDto getData(@PathVariable Integer id) {
        Input input = inputRepository.findById(id).get();
        InputDto inputDTO = new InputDto();
        modelMapper.map(input, inputDTO);
        inputDTO.setId(input.getId());
        return  inputDTO;
    }

//    @DeleteMapping
//    public void del(){
//        inputRepository.deleteAll();
//    }

//    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        inputRepository.deleteById(id);
    }

}
