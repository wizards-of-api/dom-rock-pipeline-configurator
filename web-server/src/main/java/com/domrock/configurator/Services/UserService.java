package com.domrock.configurator.Services;

import com.domrock.configurator.Config.PasswordConfig;
import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.*;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.AccountDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

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
            AccountDTO userTemp = new AccountDTO(user.getName(),user.getEmail(),user.getPassword(),user.getCompanyCnpj().getCnpj(),user.getCompanyCnpj().getFantasyName(),user.getPermission().getType());
            if (!userTemp.getEmail().equals("superuser@wiz.com"))
                usersDTO.add(userTemp);
        }

        if (users.isEmpty()) {
            throw new NoSuchElementException("Nenhum usuario encontrado");
        } else {
            return usersDTO;
        }
    }
    @Transactional
    public void updateUser(AccountDTO accountDTO) {
        Permission newPermission = permissionRepository.findByType(accountDTO.getPermission());
        User editedUser = userRepository.getReferenceById(accountDTO.getEmail());
        editedUser.setEmail(accountDTO.getEmail());
        editedUser.setName(accountDTO.getName());
        editedUser.setPermission(newPermission);
        userRepository.save(editedUser);
    }

    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

    }
    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }
}