package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
