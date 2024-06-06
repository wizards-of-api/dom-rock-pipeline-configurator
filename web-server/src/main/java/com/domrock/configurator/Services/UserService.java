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

import java.time.LocalDateTime;
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
     * Sets the permission for the user with the given email.
     *
     * @param email           the email of the user for whom the permission should be set.
     * @param permissionId    the ID of the permission to be set.
     * @return                {@link UserDTO} representing the user entity after setting the permission.
     * @throws NoSuchElementException if no user is found with the email or no permission is found with the ID.
     */
    @Transactional
    public UserDTO setUserPermission(String email, Integer permissionId) {
        User user = userRepository.findById(email).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("No user found with email: " + email);
        } else {
            Permission permission = permissionRepository.findById(permissionId).orElse(null);
            if (permission == null) {
                throw new NoSuchElementException("No permission found with ID: " + permissionId);
            }
            user.setPermission(permission);
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }
    }

    // Used by AuthenticationService, so it needs to be Entity instead of DTO
    @Transactional
    public User saveUser(User user) {
        if (user.getEmail() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}
