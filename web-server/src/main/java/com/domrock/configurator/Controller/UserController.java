package com.domrock.configurator.Controller;

import com.domrock.configurator.Interface.PermissionRepository;
import com.domrock.configurator.Model.ConfigModel.DTOConfig.AccountDTO;
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

    @GetMapping("/get-all-users")
    public ResponseEntity<List<AccountDTO>> getAllUsers() {
        try {
            List<AccountDTO> signupRequestDTOS = userService.getAllUsers();
            return ResponseEntity.ok(signupRequestDTOS);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/create-user")
    public ResponseEntity<JwtAuhenticationResponseDTO> createUser(@RequestBody SignupRequestDTO signupRequestDTO) {

            authenticationService.signup(signupRequestDTO);
            return ResponseEntity.ok(authenticationService.signup(signupRequestDTO));

//        catch (Exception e){
//            return ResponseEntity.notFound().build();
//        }

    }

    @PostMapping("/updateuser")
    public ResponseEntity<AccountDTO> updateUser(@RequestBody AccountDTO accountDTO) {
        try {
            userService.updateUser(accountDTO);
            return ResponseEntity.ok(accountDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/deleteuser/{useremail}")
    public void deleteUser(@PathVariable("useremail") String userEmail) {
        try{
            userService.deleteUser(userEmail);
        } catch (Exception e) {
            System.out.println("User with email " + userEmail + " could not be deleted.");
        }
    }


}
