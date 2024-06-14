package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

//    public List<UserDTO> getUsersByPermission(int permissionType) {
//        Permission permission = permissionRepository.findById(permissionType);
//        if (permission == null) {
//            throw new NoSuchElementException("Permission not found: " + permissionType);
//        }
//        Set<User> users = userPermissionRepository.findAllUsersByPermissionId(permissionType);
//        return users.stream()
//                .map(user -> modelMapper.map(user, UserDTO.class))
//                .collect(Collectors.toList());
//    }
}