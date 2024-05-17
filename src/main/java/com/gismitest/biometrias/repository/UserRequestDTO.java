package com.gismitest.biometrias.repository;

import org.springframework.web.multipart.MultipartFile;

public record UserRequestDTO(String firstName, String lastName, String cpf, MultipartFile image) {
}
