package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserPermissionRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.*;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.PermissionDto;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

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
                    .collect(toList());
        }
    }

    public UserDTO findByEmail(String email) {
        User user = userRepository.findById(email).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Adds a permission to the user with the given email.
     *
     * @param email           the email of the user to whom the permission should be added.
     * @param permissionCreated  the type of permission to be added.
     * @return                {@link UserDTO} representing the user entity after adding the permission.
     * @throws NoSuchElementException if no user is found with the email.
     */
    @Transactional
    public UserDTO addUserPermission(String email, int permissionCreated) {
        User user = userRepository.findById(email).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("No user found with email: " + email);
        } else {
            String permissionString = PermissionType.values()[permissionCreated].name();
            Permission adfhqifda = permissionRepository.findByType(permissionString);


            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }
    }

    /**
     * Removes a permission from the user with the given email.
     *
     * @param email           the email of the user from whom the permission should be removed.
     * @param permissionType  the type of permission to be removed.
     * @return                {@link UserDTO} representing the user entity after removing the permission.
     * @throws NoSuchElementException if no user is found with the email.
     */
    @Transactional
    public UserDTO removeUserPermission(String email, int permissionType) {
            User user = userRepository.findById(email).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("No user found with email: " + email);
        } else {
            userPermissionRepository.deleteById(new UserPermissionId(email,permissionType));
            return modelMapper.map(user, UserDTO.class);
        }
    }


    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Set<Company> companies = new HashSet<>();
        user.setCompanies(companies);
        userRepository.save(user);

    }
}
