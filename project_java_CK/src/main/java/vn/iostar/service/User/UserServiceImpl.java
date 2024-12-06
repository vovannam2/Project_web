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
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientServiceImpl clientService;

    //  default password = 1234
    @Override
    public User create(User user) {
        user.setRole(Role.builder().roleId(1).name("USER").build());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        /*
             this is return password after create password send to mail user!
             step 1: after create password -> and it save password in clientSer
             step 2: save it in user and encoder!
        */
        String password = clientService.create(user);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }
    public boolean loginUser(String email, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10 );
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new HandleException(StateErrorCode.USER_NOT_FOUND);
        }
        System.out.println(user.getPassword() + " . " + password);
        if(userRepository.existsByEmail(email)){
            System.out.println("sai mat khau");
            //return(passwordEncoder.matches(password, user.getPassword()));
            return passwordEncoder.matches(password, user.getPassword());
        }
        else {
            System.out.println("sai email");
            return false;
        }
    }
    public User getUser(String email){
        return userRepository.findByEmail(email);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
