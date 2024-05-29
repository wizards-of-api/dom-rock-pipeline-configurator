package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, String> {
}