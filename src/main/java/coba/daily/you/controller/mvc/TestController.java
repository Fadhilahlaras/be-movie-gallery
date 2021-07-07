package coba.daily.you.controller.mvc;

import coba.daily.you.model.DefaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("/test")
public class TestController {

    // Cek Role dari user ketika login

    @GetMapping(path = "/anonym")
    public DefaultResponse getToken(){
        return DefaultResponse.ok("Gak Perlu token");
    }

    @GetMapping(path = "/role")
    @RolesAllowed({"ROLE_ADMIN"})
    public DefaultResponse getData(){
        return DefaultResponse.ok("Perlu Token");
    }

}
