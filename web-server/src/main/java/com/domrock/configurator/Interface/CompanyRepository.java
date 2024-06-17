package com.domrock.configurator.Interface;

import com.domrock.configurator.Model.ConfigModel.Company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, String> {
    @Query(value = "SELECT c.fantasy_name, COUNT(uc.user_email) FROM company c " +
                   "JOIN user_company uc ON c.cnpj = uc.company_cnpj " +
                   "GROUP BY c.fantasy_name", nativeQuery = true)
    List<Object[]> getNumberOfUsersByCompany();

    @Query(value = "select c.fantasy_name, count(lz.file_id) as contagem from company c join lz_config lz on lz.cnpj = c.cnpj group by c.fantasy_name", nativeQuery = true)
    List<Object[]> findConfigsByCompany();

    public Company findByFantasyName(String name);
}
