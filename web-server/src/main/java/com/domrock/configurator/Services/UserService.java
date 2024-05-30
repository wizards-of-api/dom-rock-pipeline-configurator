package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.Permission;
import com.domrock.configurator.Model.ConfigModel.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
                return userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException(userEmail));
            }
        };
    }

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

    /**
     * Adds a permission to the user with the given email.
     *
     * @param email           the email of the user to whom the permission should be added.
     * @param permissionType  the type of permission to be added.
     * @return                {@link UserDTO} representing the user entity after adding the permission.
     * @throws NoSuchElementException if no user is found with the email.
     */
    @Transactional
    public UserDTO addUserPermission(String email, String permissionType) {
        User user = userRepository.findById(email).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("No user found with email: " + email);
        } else {
            Permission permission = permissionRepository.findByType(permissionType);
            user.getPermissions().add(permission);
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
    public UserDTO removeUserPermission(String email, String permissionType) {
        User user = userRepository.findById(email).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("No user found with email: " + email);
        } else {
            Permission permission = permissionRepository.findByType(permissionType);
            user.getPermissions().remove(permission);
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }
    }
}
