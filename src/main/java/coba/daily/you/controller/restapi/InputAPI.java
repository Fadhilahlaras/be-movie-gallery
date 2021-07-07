package coba.daily.you.controller.restapi;

import coba.daily.you.model.dto.InputDTO;
import coba.daily.you.model.entity.Input;
import coba.daily.you.repository.InputRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
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
public class InputAPI {
    @Autowired
    private InputRepository inputRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InputAPI(InputRepository inputRepository, ModelMapper modelMapper) {
        this.inputRepository = inputRepository;
        this.modelMapper = modelMapper;
    }

//    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping() //Get data return inputDTOList
    public List<InputDTO> getList() {
        //Create container to contain array input dari repo input
        List<Input> inputList = inputRepository.findAll();
        //Create container
        List<InputDTO> inputDTOList =
                inputList.stream()
                        .map(in -> mapInputToInputDto(in))
                        .collect(Collectors.toList());

        return inputDTOList;
    }

    private InputDTO mapInputToInputDto(Input input) {
        InputDTO inputDTO = modelMapper.map(input, InputDTO.class);
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
    public InputDTO editSaveData(@RequestPart(value="data", required = true) InputDTO inputDTO, @RequestPart(value="pictureUrl", required = false) MultipartFile file) throws Exception {
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
        InputDTO inputDTO1 = mapInputToInputDto(input);
        return inputDTO1;
    }

//    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public InputDTO getData(@PathVariable Integer id) {
        Input input = inputRepository.findById(id).get();
        InputDTO inputDTO = new InputDTO();
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
