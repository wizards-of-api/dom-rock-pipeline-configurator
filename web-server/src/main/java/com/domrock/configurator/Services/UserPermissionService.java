package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Interface.UserPermissionRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.Permission;
import com.domrock.configurator.Model.ConfigModel.User;
import com.domrock.configurator.Model.ConfigModel.UserPermission;
import com.domrock.configurator.Model.ConfigModel.UserPermissionId;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionService {
    @Autowired
    private UserPermissionRepository userPermissionRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public UserPermission createUserPermission(User user, int permissionId) {
        Permission permission = permissionRepository.findById(permissionId);
        UserPermissionId userPermissionId = new UserPermissionId(user.getEmail(),permission.getId());
        UserPermission userPermission = new UserPermission(userPermissionId,user,permission);
        userPermissionRepository.save(userPermission);
        return userPermission;
    }
}
