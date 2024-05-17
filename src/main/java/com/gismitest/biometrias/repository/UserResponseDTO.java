package com.gismitest.biometrias.repository;

import com.gismitest.biometrias.entities.User;

public record UserResponseDTO (Long id, String firstName, String lastName, String cpfHash, String image){

    public UserResponseDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getCpfHash(), user.getImage());
    }
}
