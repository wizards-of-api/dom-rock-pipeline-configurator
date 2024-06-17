package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.Company;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.CompanyDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:5173")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        try{
            List<Company> companies = companyService.getAllCompanies();
            List<CompanyDTO> companyDTOS = new ArrayList<>();
            for (Company company : companies) {
                CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);
                companyDTOS.add(companyDTO);
            }
            return ResponseEntity.ok(companyDTOS);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/create-company")
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO enterprise ) {
        try {
            CompanyDTO companyDTO = companyService.createCompany(enterprise);
            return ResponseEntity.ok(companyDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<CompanyDTO> removeCompanyUser(@PathVariable("cnpj") String cnpj) {
        try {
            CompanyDTO companyDTO = companyService.removeCompany(cnpj);
            return ResponseEntity.ok(companyDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/usersByCompany")
    public Map<String, Integer> getNumberOfUsersByCompany() {
        List<Object[]> data = companyService.getNumberOfUsersByCompany();
        Map<String, Integer> result = new HashMap<>();
        
        for (Object[] record : data) {
            String companyName = (String) record[0];
            Long userCount = (Long) record[1];
            result.put(companyName, userCount.intValue());
        }
        
        return result;
    }

    @GetMapping("/allConfigsByCompanies")
    public Map<String, Integer> getAllConfigsByCompany() {
        List<Object[]> configsCompany = companyService.getConfigsbyCompanies();
        Map<String, Integer> configs = new HashMap<>();

        for (Object[] itens : configsCompany) {
            String companyName = (String) itens[0];
            Long configsCont = (Long) itens[1];
            configs.put(companyName, configsCont.intValue());
        }
        
        return configs;
    }
}