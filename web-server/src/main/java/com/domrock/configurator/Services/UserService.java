package com.domrock.configurator.Services;

import com.domrock.configurator.Config.PasswordConfig;
import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.*;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.AccountDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SignupRequestDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordConfig bCryptPasswordEncoder;

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
    public List<AccountDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<AccountDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            AccountDTO userTemp = new AccountDTO(user.getName(),user.getEmail(),user.getPassword(),user.getCompanyCnpj().getCnpj(),user.getPermission().getType());
            usersDTO.add(userTemp);
        }

        if (users.isEmpty()) {
            throw new NoSuchElementException("Nenhum usuario encontrado");
        } else {
            return usersDTO;
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


    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

    }
}
