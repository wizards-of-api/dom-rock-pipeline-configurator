package com.domrock.configurator.Controller;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.JwtAuhenticationResponseDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.SignupRequestDTO;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.UserDTO;
import com.domrock.configurator.Services.AuthenticationService;
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
    private AuthenticationService authenticationService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> userDTOS = userService.getAllUsers();
            return ResponseEntity.ok(userDTOS);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/create-user")
    public ResponseEntity<JwtAuhenticationResponseDTO> createUser(@RequestBody SignupRequestDTO signupRequestDTO) {
        try{
            authenticationService.signup(signupRequestDTO);
            return ResponseEntity.ok(authenticationService.signup(signupRequestDTO));
        }catch (Exception e){
            System.out.println(e.getMessage());
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

//    @DeleteMapping("/{userEmail}/permissions/{permissionType}")
//    public ResponseEntity<UserDTO> removePermission(@PathVariable("userEmail") String userEmail,
//                                                    @PathVariable("permissionType") int permissionType) {
//        try {
//            UserDTO userDTO = userService.removeUserPermission(userEmail, permissionType);
//            return ResponseEntity.ok(userDTO);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

}
