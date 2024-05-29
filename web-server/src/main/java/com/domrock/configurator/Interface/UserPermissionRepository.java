package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.UserPermission;
import com.domrock.configurator.Model.ConfigModel.UserPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionId> {
    @Query(value = "select * from domrock.user_permission where user_email = :userid", nativeQuery = true)
    public List<UserPermission> findByUserEmail(@Param("userid") String userId);
}