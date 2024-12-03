package vn.iostar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;
import vn.iostar.exception.HandleException;
import vn.iostar.exception.StateErrorCode;
import vn.iostar.repository.UserRepository;
import vn.iostar.service.User.IUserService;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserRepository userRepository;
    //  default password = 1234
    @Override
    public void create(User user){
        user.setRole(Role.builder().roleId(1).name("USER").build());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            return(passwordEncoder.matches(password, user.getPassword()));
        else {
            return false;
        }
    }
}
