package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.CompanyRepository;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.Company;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.CompanyDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.User;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Creates a new company based on the provided DTO.
     *
     * @param companyDTO the DTO containing the information of the company to be created.
     * @return           {@link CompanyDTO} representing the newly created company entity.
     */
    @Transactional
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyDTO.class);
    }

    @Transactional
    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public CompanyDTO removeCompany(String cnpj) {
        Company company = companyRepository.findById(cnpj).orElse(null);
        if(company != null){
            companyRepository.delete(company);
        }
        return modelMapper.map(company, CompanyDTO.class);
    }

    /**
     * Retrieves all users belonging to the company with the specified CNPJ.
     *
     * @param cnpj the CNPJ of the company.
     * @return     {@link List<UserDTO>} representing the list of users associated with the company.
     * @throws NoSuchElementException if no company is found with the specified CNPJ.
     */
    @Transactional
    public List<UserDTO> getCompanyUsers(String cnpj) {
        Company company = companyRepository.findById(cnpj).orElse(null);
        if (company == null) {
            throw new NoSuchElementException("No company found with CNPJ: " + cnpj);
        } else {
            return company.getUsers().stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());
        }
    }

}
