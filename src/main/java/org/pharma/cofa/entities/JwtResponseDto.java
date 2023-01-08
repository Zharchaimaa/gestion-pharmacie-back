package org.pharma.cofa.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Jwt Response DTO
 *
 * @Author: OUMEIMA ZINAOUI
 * @Version: 1.0
 * <p>
 * Modèle de réponse d'authentification
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponseDto implements Serializable {

    @Schema(description = "Nom et prénom d'utilisateur", example = "oumeima zinaoui")
    private String nom;

    @Schema(description = "Profil d'utilisateur", example = "admin")
    private String profil;

    @Schema(description = "Jeton", example = "Jeton compatible JWT")
    private String token;

}
