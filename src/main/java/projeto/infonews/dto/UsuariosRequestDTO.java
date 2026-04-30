package projeto.infonews.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuariosRequestDTO(
        @NotBlank @Email
        String email,

        @NotBlank @Size(min = 6)
        String senha,

        @NotBlank
        String confirmacaoSenha
) {}