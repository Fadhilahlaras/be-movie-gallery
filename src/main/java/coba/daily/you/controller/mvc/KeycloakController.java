package coba.daily.you.controller.mvc;

import coba.daily.you.model.DefaultResponse;
import coba.daily.you.model.dto.request.LoginRequestDto;
import coba.daily.you.webclient.KeyCloakWebClient;
import coba.daily.you.model.dto.keycloak.TokenResponseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class KeycloakController {
    private final KeyCloakWebClient keyCloakWebClient;

    public KeycloakController(KeyCloakWebClient keyCloakWebClient) {
        this.keyCloakWebClient = keyCloakWebClient;
    }

    // Request Token yg mempunyai type data form url ....
    @PostMapping(path = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public DefaultResponse<TokenResponseDto> getToken(LoginRequestDto loginRequestDto){
        //Validasi token
        TokenResponseDto tokenResponseDto = keyCloakWebClient.getToken(loginRequestDto);
        return DefaultResponse.ok(tokenResponseDto);
    }

}
