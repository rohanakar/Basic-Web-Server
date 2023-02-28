package rishabh.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rishabh.server.models.Vault;
import rishabh.server.repositories.VaultRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class VaultService {
    
    @Autowired
    private VaultRepository vaultRepository;
    
    Logger logger = LoggerFactory.getLogger(VaultService.class);
    
    public boolean isAuthenticated(String authHeader){
        
        String values[] = getCredentials(authHeader);
        Vault vault =  vaultRepository.findByKey(values[0]);
        return AuthUtils.match(values[1],vault.getValue());
    }
    
    public String[] getCredentials(String authHeader){
        if(!authHeader.startsWith("Basic"))
            throw new RuntimeException("Invalid AuthHeader");

        String base64Credentials = authHeader.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        return credentials.split(":", 2);
    }
}
