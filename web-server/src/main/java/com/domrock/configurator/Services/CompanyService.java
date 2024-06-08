package com.domrock.configurator.Services;

import com.domrock.configurator.Interface.CompanyRepository;
import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import com.domrock.configurator.Interface.UserRepository;
import com.domrock.configurator.Model.ConfigModel.Company;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.CompanyDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ConfigsCompanyDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Model.ConfigModel.User;
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
    private LZMetadataConfigInterface lzMetadataConfigInterfaces;

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

    /**
     * Adds a user to the company with the given CNPJ.
     *
     * @param cnpj      the CNPJ of the company to which the user should be added.
     * @param userEmail the email of the user to be added.
     * @return          {@link CompanyDTO} representing the company entity after adding the user.
     * @throws NoSuchElementException if no company is found with the specified CNPJ,
     *                                or if no user is found with the specified email.
     */
    @Transactional
    public CompanyDTO addCompanyUser(String cnpj, String userEmail) {
        Company company = companyRepository.findById(cnpj).orElse(null);
        if (company == null) {
            throw new NoSuchElementException("No company found with CNPJ: " + cnpj);
        } else {
            User user = userRepository.findById(userEmail).orElse(null);
            if (user == null) {
                throw new NoSuchElementException("No user found with email: " + userEmail);
            } else {
                company.getUsers().add(user);
                companyRepository.save(company);
                return modelMapper.map(company, CompanyDTO.class);
            }
        }
    }

    /**
     * Removes a user from the company with the specified CNPJ.
     *
     * @param cnpj      the CNPJ of the company from which the user should be removed.
     * @param userEmail the email of the user to be removed.
     * @return          {@link CompanyDTO} representing the company entity after removing the user.
     * @throws NoSuchElementException if no company is found with the specified CNPJ,
     *                                or if no user is found with the specified email.
     */
    @Transactional
    public CompanyDTO removeCompanyUser(String cnpj, String userEmail) {
        Company company = companyRepository.findById(cnpj).orElse(null);
        if (company == null) {
            throw new NoSuchElementException("No company found with CNPJ: " + cnpj);
        } else {
            User user = userRepository.findById(userEmail).orElse(null);
            if (user == null) {
                throw new NoSuchElementException("No user found with email: " + userEmail);
            } else {
                company.getUsers().remove(user);
                companyRepository.save(company);
                return modelMapper.map(company, CompanyDTO.class);
            }
        }
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

    // Chama o número de Usuários por Empresa
    public List<Object[]> getNumberOfUsersByCompany() {
        return companyRepository.getNumberOfUsersByCompany();
    }

    public List<ConfigsCompanyDTO> getConfigsbyCompanies() {
        List<Object[]> company = lzMetadataConfigInterfaces.findConfigsByCompany();
        if (company.isEmpty()) {
            throw new NoSuchElementException("No configs encountered in this company...");
        } else {
            return company.stream()
                .map(configByCompany -> new ConfigsCompanyDTO((Long) configByCompany[0], (String) configByCompany[1]))
                .collect(Collectors.toList());
        }
    }
}
