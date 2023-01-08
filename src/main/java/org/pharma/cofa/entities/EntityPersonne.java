package org.pharma.cofa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityPersonne implements UserDetails {

    @Schema(description = "Matricule", example = "123456", required = true)
    @Id
    private String matricule;

    @Schema(description = "Entité", example = "ABC123", required = true)
    private String entite;

    @Schema(description = "password", example = "ABC123", required = true)
    private String password;

    private String nom;
    private String prenom;
    @Schema(description = "Profil", example = "Profil", required = true)
    private String profil;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> profil);
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}

/*public class EntityPersonne implements UserDetails {
    @Id
    private String cne;
    private String nom;
    private String prenom;
    private String role;

    @Email(message = "Svp entrez une adresse email valid!")
    @NotEmpty(message = "Veuillez fournir un courriel ! Ce champ ne peut pas être vide!")
    private String username;

    @NotEmpty(message = "Veuillez fournir un mot de passe ! Ce champ ne peut pas être vide")
    @Size(min = 3)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}*/

