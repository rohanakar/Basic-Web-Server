package rishabh.server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rishabh.server.DTOs.RegistrationDTO;
import rishabh.server.models.User;
import rishabh.server.models.Vault;
import rishabh.server.repositories.UserRepository;
import rishabh.server.repositories.VaultRepository;

import java.util.UUID;

@Service
@Qualifier("UserServiceBasicImpl")
public class UserServiceBasicImpl implements UserService{
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VaultRepository vaultRepository;
    
    @Override
    @Transactional
    public Long register(RegistrationDTO registrationDTO) {
       
        User user = modelMapper.map(registrationDTO,User.class);
        Vault vault = new Vault();
        vault.setCode(UUID.randomUUID().toString().substring(8));
        vault.setKey(user.getEmail());
        vault.setValue(AuthUtils.salt(registrationDTO.getPassword()));
        vaultRepository.save(vault);
        return userRepository.save(user).getId();
    }

    @Override
    public void update(RegistrationDTO registrationDTO) {

        User user = modelMapper.map(registrationDTO,User.class);
        userRepository.save(user);
    }

    @Override
    public User get(Long id) throws Exception{
        return userRepository.getById(id);
    }

    @Override
    public User getByEmail(String email) throws Exception {
        return userRepository.getByEmail(email);
    }
}
