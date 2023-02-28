package rishabh.server.service;

import org.springframework.stereotype.Service;
import rishabh.server.DTOs.RegistrationDTO;
import rishabh.server.models.User;

@Service
public interface UserService {

    Long register(RegistrationDTO registrationDTO);
    void update(RegistrationDTO registrationDTO);
    User get(Long id) throws Exception;
    User getByEmail(String email) throws Exception;
}
