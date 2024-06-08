package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.CompanyDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ConfigsCompanyDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.ListConfigsByCompanyDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:5173")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{cnpj}/users")
    public ResponseEntity<List<UserDTO>> getCompanyUsers(@PathVariable("cnpj") String cnpj) {
        try {
            List<UserDTO> userDTOS = companyService.getCompanyUsers(cnpj);
            return ResponseEntity.ok(userDTOS);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cnpj}/users/{userEmail}")
    public ResponseEntity<CompanyDTO> addCompanyUser(@PathVariable("cnpj") String cnpj,
                                                     @PathVariable("userEmail") String userEmail) {
        try {
            CompanyDTO companyDTO = companyService.addCompanyUser(cnpj, userEmail);
            return ResponseEntity.ok(companyDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{cnpj}/users/{userEmail}")
    public ResponseEntity<CompanyDTO> removeCompanyUser(@PathVariable("cnpj") String cnpj,
                                                        @PathVariable("userEmail") String userEmail) {
        try {
            CompanyDTO companyDTO = companyService.removeCompanyUser(cnpj, userEmail);
            return ResponseEntity.ok(companyDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/allConfigsByCompanies")
    public ResponseEntity<ListConfigsByCompanyDTO> getAllConfigsByCompany() {
        List<ConfigsCompanyDTO> configsCompany = companyService.getConfigsbyCompanies();
        try {
            ListConfigsByCompanyDTO allConfigsByCompany = new ListConfigsByCompanyDTO(configsCompany);
            return ResponseEntity.ok(allConfigsByCompany);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}