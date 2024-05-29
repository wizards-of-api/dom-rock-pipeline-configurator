package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
  public Permission findByType(String type);
  public Permission findById(int id);
  }