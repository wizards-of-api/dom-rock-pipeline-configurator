package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
