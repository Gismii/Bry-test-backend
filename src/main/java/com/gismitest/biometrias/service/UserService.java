package com.gismitest.biometrias.service;

import com.gismitest.biometrias.entities.User;
import com.gismitest.biometrias.fileStorageService.FileStorageService;
import com.gismitest.biometrias.repository.UserRepository;
import com.gismitest.biometrias.repository.UserRequestDTO;
import com.gismitest.biometrias.repository.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        try {
            String imageUrl = fileStorageService.storeFile(userRequestDTO.image());
            String hashedCpf = CpfHashingService.hashCpf(userRequestDTO.cpf());

            User user = new User();
            user.setFirstName(userRequestDTO.firstName());
            user.setLastName(userRequestDTO.lastName());
            user.setCpfHash(hashedCpf);
            user.setImage(imageUrl);

            User savedUser = userRepository.save(user);
            return new UserResponseDTO(savedUser);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing CPF", e);
        }
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            String hashedCpf = CpfHashingService.hashCpf(userRequestDTO.cpf());

            user.setFirstName(userRequestDTO.firstName());
            user.setLastName(userRequestDTO.lastName());
            user.setCpfHash(hashedCpf);
            user.setImage(fileStorageService.storeFile(userRequestDTO.image()));

            User updatedUser = userRepository.save(user);
            return new UserResponseDTO(updatedUser);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing CPF", e);
        }
    }

    public UserResponseDTO updateUserNamesAndImage(Long id, String firstName, String lastName, MultipartFile image) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setImage(fileStorageService.storeFile(image));

        User updatedUser = userRepository.save(user);
        return new UserResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
