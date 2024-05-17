package com.gismitest.biometrias.repository;

import com.gismitest.biometrias.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM users u")
    List<User> findAllWithEncryptedCpf(); // Retorna todos os usuários com CPF criptografado

    @Query("SELECT u FROM users u")
    List<User> findAllWithDecryptedCpf(); // Retorna todos os usuários com CPF descriptografado
}