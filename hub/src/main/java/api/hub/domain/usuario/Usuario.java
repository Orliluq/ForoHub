package api.hub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;

    public Usuario(RegistroUsuarioDTO registroUsuarioDTO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.name = registroUsuarioDTO.name();
        this.email = registroUsuarioDTO.email();
        this.username = registroUsuarioDTO.username();
        this.password = bCryptPasswordEncoder.encode(registroUsuarioDTO.password());
        this.active = true;
    }

    public void actualizacionUsuario(ActualizacionUsuarioDTO actualizacionUsuarioDTO) {
        if (actualizacionUsuarioDTO.name() != null) {
            this.name = actualizacionUsuarioDTO.name();
        }
        /*if (actualizacionUsuarioDTO.email() != null) {
            this.email = actualizacionUsuarioDTO.email();
        }*/
    }

    public void deactivateUser() {
        this.active = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
