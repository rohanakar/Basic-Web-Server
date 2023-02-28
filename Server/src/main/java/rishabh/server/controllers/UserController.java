package rishabh.server.controllers;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rishabh.server.DTOs.RegistrationDTO;
import rishabh.server.models.User;
import rishabh.server.models.Vault;
import rishabh.server.service.UserService;
import rishabh.server.service.VaultService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    
    Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    @Qualifier("UserServiceBasicImpl")
    private UserService userService;

    @Autowired
    private VaultService vaultService;
    
    static final char SEPARATOR = '/';
    
    @PostMapping("/register")
    public ResponseEntity register(HttpServletRequest request, @RequestBody final RegistrationDTO registrationDTO){
        try{
           Long id =  userService.register(registrationDTO);
           String base = request.getRequestURL().toString().replace(request.getRequestURI(), "");
           return ResponseEntity.created(URI.create(StringUtils.join(List.of(base,"users",id.toString()), SEPARATOR))).build();
        }catch(Exception e){
            logger.info("Exception occured - {}",e.toString());
            return ResponseEntity.status(500).body("Error");
        }
        
    }

    @PostMapping("/reset-password/{code}")
    public ResponseEntity resetPassword(@RequestBody final RegistrationDTO registrationDTO, @PathVariable("code") String code){
        return ResponseEntity.ok(null);
    }

    @PostMapping("/login/{email}/forgot-password")
    public ResponseEntity forgotPassword(@PathVariable("email") String email){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity login(@PathVariable final Long id){
        try{
            User user = userService.get(id);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            logger.info("Exception occured - {}",e.toString());
            return ResponseEntity.status(500).body("Error");
        }
    }

    @GetMapping("")
    public ResponseEntity login(@RequestHeader("Authorization") String authorization){
        try{
            String[] credentials = vaultService.getCredentials(authorization);
            User user = userService.getByEmail(credentials[0]);
            return ResponseEntity.ok(user);
        }catch(Exception e){
            logger.info("Exception occured - {}",e.toString());
            return ResponseEntity.status(500).body("Error");
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity update(@RequestBody final RegistrationDTO registrationDTO){
        try{
            userService.update(registrationDTO);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            logger.info("Exception occured - {}",e.toString());
            return ResponseEntity.status(500).body("Error");
        }
    }
    
}
