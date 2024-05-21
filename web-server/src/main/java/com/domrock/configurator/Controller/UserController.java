package com.domrock.configurator.Controller;

import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> userDTOS = userService.getAllUsers();
            return ResponseEntity.ok(userDTOS);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/create-user/{permissionType}")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO , @PathVariable int permissionType) {
        try{
            userService.createUser(userDTO);
            userService.addUserPermission(userDTO.getEmail(),permissionType-1);
            return ResponseEntity.ok(modelMapper.map(userDTO, UserDTO.class));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/{userEmail}/permissions/{permissionType}")
    public ResponseEntity<UserDTO> addPermission(@PathVariable("userEmail") String userEmail,
                                                 @PathVariable("permissionType") int permissionType) {
        try {
            UserDTO userDTO = userService.addUserPermission(userEmail, permissionType);
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{userEmail}/permissions/{permissionType}")
    public ResponseEntity<UserDTO> removePermission(@PathVariable("userEmail") String userEmail,
                                                    @PathVariable("permissionType") String permissionType) {
        try {
            UserDTO userDTO = userService.removeUserPermission(userEmail, permissionType);
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
