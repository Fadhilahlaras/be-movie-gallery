package coba.daily.you.controller.restapi;


import coba.daily.you.model.dto.InputDto;
import coba.daily.you.model.entity.Input;
import coba.daily.you.repository.InputRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/upload")
public class ApiImage {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InputRepository inputRepository;

    private InputDto mapToDTO(Input input) {
        InputDto inputDTO = modelMapper.map(input, InputDto.class);
        return inputDTO;
    }
}
