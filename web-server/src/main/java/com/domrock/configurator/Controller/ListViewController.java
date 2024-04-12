package com.domrock.configurator.Controller;


import com.domrock.configurator.Interface.LZMetadataConfigInterface;
import com.domrock.configurator.Model.ConfigModel.LZMetadataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/list-view")
public class ListViewController {

    @Autowired
    LZMetadataConfigInterface lzRepository;

    //@Autowired
    //void lzSearchId;

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

    // @DeleteMapping("/delete/{id}")
    // public void deleteById (Integer id) {
    //     LZMetadataConfig delete = lzId.deleteById(id);
    // }
}       