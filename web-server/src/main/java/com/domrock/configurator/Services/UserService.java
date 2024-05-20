package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.Permission;
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
    private PermissionRepository permissionRepository;

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

    public UserDTO findByEmail(String email) {
        User user = userRepository.findById(email).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO addUserPermission(String email, String permissionType) {
        User user = userRepository.findById(email).orElseThrow(NoSuchElementException::new);
        Permission permission = permissionRepository.findPermissionByType(permissionType);
        user.getPermissions().add(permission);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO removeUserPermission(String email, String permissionType) {
        User user = userRepository.findById(email).orElseThrow(NoSuchElementException::new);
        Permission permission = permissionRepository.findPermissionByType(permissionType);
        user.getPermissions().remove(permission);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
}
