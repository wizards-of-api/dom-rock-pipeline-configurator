package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Services.PermissionService;
import com.domrock.configurator.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/permission")
@CrossOrigin(origins = "http://localhost:5173")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

//    @GetMapping("/{permissionType}/users")
//    public ResponseEntity<List<UserDTO>> getUsersByPermission(@PathVariable("permissionType") int permissionType) {
//        try {
//            List<UserDTO> userDTOS = userService.;
//            return ResponseEntity.ok(userDTOS);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
}