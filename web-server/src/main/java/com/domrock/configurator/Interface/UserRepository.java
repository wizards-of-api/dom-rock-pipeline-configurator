package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.SignupRequestDTO;
import com.domrock.configurator.Model.ConfigModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    List<User> findAll();
}
