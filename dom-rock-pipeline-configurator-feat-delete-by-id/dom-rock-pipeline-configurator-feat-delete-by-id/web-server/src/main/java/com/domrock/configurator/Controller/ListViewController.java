package com.domrock.configurator.Controller;


import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import com.domrock.configurator.Services.LZMetadataConfigServices;

import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/list-view")
public class ListViewController {

    @Autowired
    LZMetadataConfigInterface lzRepository;

    @GetMapping
    public ResponseEntity<Page<LZMetadataConfig>> listDoctor(@PageableDefault(size = 16, sort={"name"}) Pageable paginator){
        Page<LZMetadataConfig> page = lzRepository.findAll(paginator);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity details(@PathVariable Integer id){
        LZMetadataConfig config = lzRepository.getReferenceById(id);
        return ResponseEntity.ok(config);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById (@PathVariable Integer id) {
        lzRepository.deleteById(id);
        
        System.out.println("Ok");
        //Optional<LZMetadataConfig> delete = lzRepository.findById(id); 
        
        // if (delete.isEmpty()) {
        //     throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // } else {
        //     lzRepository.delete(delete.get());
        //     System.out.println("Ok");
        // }
    }
}       