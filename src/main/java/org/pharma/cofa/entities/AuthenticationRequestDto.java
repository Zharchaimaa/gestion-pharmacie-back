package org.pharma.cofa.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Authentication Request DTO
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 * <p>
 * Modèle de requête d'authentification
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequestDto implements Serializable {
    @Schema(description = "Matricule d'utilisateur", example = "1234", required = true)
    @NotBlank(message = "matricule ne peut pas être vide")
    public String matricule;

    @Schema(description = "Profil", example = "SAISI", required = true)
    @NotBlank(message = "profil ne peut pas être vide")
    public String profil;
}
