package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.Permission;
import com.domrock.configurator.Model.ConfigModel.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getUsersByPermission(String permissionType) {
        Permission permission = permissionRepository.findByType(permissionType);
        if (permission == null) {
            throw new NoSuchElementException("Permission not found: " + permissionType);
        }
        Set<User> users = permission.getUsers();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}