package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves and converts to DTO all users from the repository.
     *
     * @return {@link UserDTO} List representing all User entities.
     * @throws NoSuchElementException if no users are found in the repository.
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoSuchElementException("Nenhum usuario encontrado");
        } else {
            return users.stream()
                    .map((user) -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());
        }
    }
}
