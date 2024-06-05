package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.User;
import com.domrock.configurator.Model.ConfigModel.UserPermission;
import com.domrock.configurator.Model.ConfigModel.UserPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionId> {
    @Query(value = "select * from domrock.user_permission where user_email = :userid", nativeQuery = true)
    public List<UserPermission> findByUserEmail(@Param("userid") String userId);
    @Query(value = "select u.* from user_permission as up join user as u on up.user_email = u.email where up.permission_id = :perm_id", nativeQuery = true)
    public Set<User> findAllUsersByPermissionId(@Param("perm_id") int perm_id);
}