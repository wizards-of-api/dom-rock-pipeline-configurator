package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserPermissionRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.Permission;
import com.domrock.configurator.Model.ConfigModel.User;
import com.domrock.configurator.Model.ConfigModel.UserPermission;
import com.domrock.configurator.Model.ConfigModel.UserPermissionId;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionService {
    @Autowired
    private UserPermissionRepository userPermissionRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void createUserPermission(UserDTO userDTO) {
        Boolean lzbool = userDTO.isLzbool();
        Boolean bronzebool = userDTO.isBronzebool();
        Boolean silverbool = userDTO.isBronzebool();
        User user = modelMapper.map(userDTO, User.class);
        for (int i = 0; i < 3; i++) {
            if(lzbool) {
                Permission permission = permissionRepository.findById(1);
                UserPermissionId userPermissionId = new UserPermissionId(userDTO.getEmail(),permission.getId());
                UserPermission userPermission = new UserPermission(userPermissionId,user,permission);
                userPermissionRepository.save(userPermission);
            }
            if(bronzebool) {
                Permission permission = permissionRepository.findById(2);
                UserPermissionId userPermissionId = new UserPermissionId(userDTO.getEmail(),permission.getId());
                UserPermission userPermission = new UserPermission(userPermissionId,user,permission);
                userPermissionRepository.save(userPermission);
            }
            if(silverbool) {
                Permission permission = permissionRepository.findById(3);
                UserPermissionId userPermissionId = new UserPermissionId(userDTO.getEmail(),permission.getId());
                UserPermission userPermission = new UserPermission(userPermissionId,user,permission);
                userPermissionRepository.save(userPermission);
            }

        }
    }
}
