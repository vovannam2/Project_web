package vn.iostar.service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;
import vn.iostar.exception.HandleException;
import vn.iostar.exception.StateErrorCode;
import vn.iostar.repository.UserRepository;
import vn.iostar.service.IUserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientServiceImpl clientService;

    //  default password = 1234
    @Override
    public void create(User user, MultipartFile image) throws IOException {
        user.setRole(Role.builder().roleId(1).name("USER").build());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        String password = clientService.create(user);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
    public boolean loginUser(String email, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10 );
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new HandleException(StateErrorCode.USER_NOT_FOUND);
        }
        System.out.println(user.getPassword() + " . " + password);
        if(userRepository.existsByEmail(email))
            //return(passwordEncoder.matches(password, user.getPassword()));
            return passwordEncoder.matches(password, user.getPassword());
        else {
            return false;
        }
    }
    public User getUser(String email){
        return userRepository.findByEmail(email);
    }
}
