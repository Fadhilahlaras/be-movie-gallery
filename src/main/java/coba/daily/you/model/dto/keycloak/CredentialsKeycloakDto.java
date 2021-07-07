package coba.daily.you.model.dto.keycloak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialsKeycloakDto {

    private String type;

    private String value;

    private Boolean temporary;

}
