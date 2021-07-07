package coba.daily.you.controller.mvc;
import coba.daily.you.model.DefaultResponse;
import coba.daily.you.model.dto.RegisterDto;
import coba.daily.you.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    RegisterController(UserService userService) {
        this.userService = userService;
    }

    //Request ketika user register
    @PostMapping("/create")
    public DefaultResponse create(@RequestBody @Valid RegisterDto registerDto) {
        //Return value yg berupa user service yang isinya register DTO
        return DefaultResponse.ok(userService.saveRegister(registerDto));
    }
}
