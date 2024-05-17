package com.gismitest.biometrias.entities;

import com.gismitest.biometrias.repository.UserRequestDTO;
import com.gismitest.biometrias.service.CpfHashingService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.security.NoSuchAlgorithmException;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O primeiro nome não pode estar em branco")
    @Size(max = 30, message = "Nome inválido")
    private String firstName;

    @NotBlank(message = "O sobrenome não pode estar em branco")
    @Size(max = 30, message = "Nome inválido")
    private String lastName;

    @NotBlank(message = "A imagem é obrigatória")
    private String image;

    @NotBlank(message = "CPF é obrigatório")
    private String cpfHash; // Armazenar o hash do CPF

    public User(UserRequestDTO data, String imageUrl) throws NoSuchAlgorithmException {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.image = imageUrl;
        this.cpfHash = CpfHashingService.hashCpf(data.cpf());
    }
}